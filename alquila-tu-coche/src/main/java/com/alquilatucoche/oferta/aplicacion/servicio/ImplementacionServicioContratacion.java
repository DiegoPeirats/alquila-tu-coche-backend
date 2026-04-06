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
import com.alquilatucoche.reserva.aplicacion.respuesta.ReservaDTO;
import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;
import com.alquilatucoche.reserva.dominio.servicio.ServicioReserva;
import lombok.RequiredArgsConstructor;
import com.alquilatucoche.usuarios.dominio.servicio.ServicioUsuario;
import com.alquilatucoche.vehiculos.dominio.servicio.ServicioVehiculo;
import com.stripe.exception.StripeException;

import jakarta.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class ImplementacionServicioContratacion implements ServicioContratacion{
	
	private final ServicioOferta servicioOferta;
	
	private final ServicioReserva servicioReserva;
	
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
		
		//cambiar el estado de la reserva
		servicioReserva.cambiarEstado(EstadoReserva.EJECUTANDO, peticion.getReservaId());
		
		//comprobar el pago	
		servicioPago.confirmarPago(peticion.getSessionId(), peticion.getDatos().getUsuarioId() , Math.round(precio));
		
		return ResultadoContratacion.builder()
				.oferta(servicioOferta.obtenerOferta(peticion.getDatos().getOfertaId()))
				.resultado("Exito")
				.build();
	}
	
	//crear metodo para ver si el proceso ha ido bien y enviar el dinero al propietario
	@Override
	@Transactional
	public String alquiladoConExito() throws StripeException {
		
		Long idCliente = servicioUsuario.miInformacion().getId();
		
		List<ReservaDTO> reservas = servicioReserva.obtenerReservas(idCliente);

		ReservaDTO reserva = reservas.get(reservas.size() - 1);
		
		Long idOferta = reserva.getOfertaId();
		
		Long idVehiculo = servicioOferta.obtenerOferta(idOferta).getIdVehiculo();
		
		Long idPropietario = servicioVehiculo.encontrarVehiculo(idVehiculo).getIdPropietario();
		
		servicioPago.enviarPago(PeticionEmisionPago.builder()
			.propietarioStripeId(servicioUsuario.cuentaStripePropietario(idPropietario))
			.build());
		
		LocalDate fechaFinAlquiler = reserva.getFechaFin();

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
	
		List<Long> idsOfertas = servicioReserva.obtenerIdOfertasCaducadas();
		
		servicioOferta.liberarOfertas(idsOfertas);
	}

}
