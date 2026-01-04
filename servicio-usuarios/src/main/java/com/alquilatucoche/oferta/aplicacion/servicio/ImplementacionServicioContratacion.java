package com.alquilatucoche.oferta.aplicacion.servicio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alquilatucoche.email.respuesta.TipoEmail;
import com.alquilatucoche.email.servicio.ServicioEmail;
import com.alquilatucoche.oferta.aplicacion.respuesta.ResultadoContratacion;
import com.alquilatucoche.oferta.dominio.servicio.ServicioContratacion;
import com.alquilatucoche.oferta.dominio.servicio.ServicioOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionContratacionOferta;
import com.alquilatucoche.pagos.dominio.servicio.ServicioPago;
import com.alquilatucoche.pagos.infraestructura.peticiones.PeticionEmisionPago;
import com.alquilatucoche.transaccion.aplicacion.respuesta.TransaccionDTO;
import com.alquilatucoche.transaccion.dominio.servicio.ServicioTransaccion;
import lombok.RequiredArgsConstructor;
import com.alquilatucoche.transaccion.infraestructura.peticiones.PeticionCreacionTransaccion;
import com.alquilatucoche.usuarios.dominio.servicio.ServicioUsuario;
import com.alquilatucoche.vehiculos.dominio.servicio.ServicioVehiculo;
import com.stripe.exception.StripeException;

import jakarta.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class ImplementacionServicioContratacion implements ServicioContratacion{
	
	private final ServicioOferta servicioOferta;
	
	private final ServicioTransaccion servicioTransaccion;
	
	private final ServicioPago servicioPago;
	
	private final ServicioUsuario servicioUsuario;
	
	private final ServicioVehiculo servicioVehiculo;
	
	private final ServicioEmail servicioEmail;

	@Override
	@Transactional
	public ResultadoContratacion contratarOferta(PeticionContratacionOferta peticion) throws StripeException {
		
		Double precio = servicioOferta.obtenerPrecioPorContratacion(peticion.getDatos().getOfertaId(), peticion.getDatos().getDiasContratados());
		
		// cambiar el estado de la oferta a contratada
		servicioOferta.establecerOfertaContratada(peticion.getDatos().getOfertaId());
		
		//crear la transaccion
		PeticionCreacionTransaccion peticionTransaccion = PeticionCreacionTransaccion.builder()
				.precio(precio)
				.idOferta(peticion.getDatos().getOfertaId())
				.idUsuario(servicioUsuario.miInformacion().getId())
				.build();
		
		TransaccionDTO transaccion = servicioTransaccion.crearTransaccion(peticionTransaccion);
		
		//comprobar el pago	
		servicioPago.confirmarPago(peticion.getSessionId(), transaccion.getId(), Math.round(precio));
		
		return ResultadoContratacion.builder()
				.oferta(servicioOferta.obtenerOferta(peticion.getDatos().getOfertaId()))
				.transaccion(transaccion)
				.resultado("Exito")
				.build();
	}
	
	//crear metodo para ver si el proceso ha ido bien y enviar el dinero al propietario
	@Override
	@Transactional
	public String alquiladoConExito() throws StripeException {
		
		Long idCliente = servicioUsuario.miInformacion().getId();
		
		TransaccionDTO transaccion = servicioTransaccion.obtenerUltimaTransaccionDelCliente(idCliente);
		
		Long idOferta = servicioTransaccion.obtenerUltimaTransaccionDelCliente(idCliente).getIdOferta();
		
		Long idVehiculo = servicioOferta.obtenerOferta(idOferta).getIdVehiculo();
		
		Long idPropietario = servicioVehiculo.encontrarVehiculo(idVehiculo).getIdPropietario();
		
		servicioPago.enviarPago(PeticionEmisionPago.builder()
			.transaccionId(transaccion.getId())
			.propietarioStripeId(servicioUsuario.busquedaUsuario(idPropietario).getNumeroDeCuenta())
			.build());
		
		LocalDate fechaFinAlquiler = LocalDate.now().plusDays(transaccion.getDiasContratados());

		//enviar alerta al cliente
		servicioEmail.enviarCorreo(servicioUsuario.miInformacion().getEmail(), 
				TipoEmail.VEHICULO_ALQUILADO.getAsunto(), 
				TipoEmail.VEHICULO_ALQUILADO.getMensajeCliente(idVehiculo, fechaFinAlquiler));

		//enviar alerta al propietario
		servicioEmail.enviarCorreo(servicioUsuario.busquedaUsuario(idPropietario).getEmail(), 
				TipoEmail.VEHICULO_ALQUILADO.getAsunto(), 
				TipoEmail.VEHICULO_ALQUILADO.getMensajeCliente(idCliente, fechaFinAlquiler));
		
		return "Gracias por confiar en nuestro servicio";
	}

	@Override
	@Transactional
	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void terminarContrato() {
		
		List<Long> idsOfertas = servicioTransaccion.obtenerIdOfertasCaducadas();
		
		servicioOferta.liberarOfertas(idsOfertas);
	}

}
