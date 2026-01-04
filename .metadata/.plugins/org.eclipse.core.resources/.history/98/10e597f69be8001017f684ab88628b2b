package com.alquilatucoche.oferta.aplicacion.servicio;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alquilatucoche.oferta.aplicacion.respuesta.ResultadoContratacion;
import com.alquilatucoche.oferta.dominio.servicio.ServicioContratacion;
import com.alquilatucoche.oferta.dominio.servicio.ServicioOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionContratacionOferta;
import com.alquilatucoche.transaccion.aplicacion.respuesta.TransaccionDTO;
import com.alquilatucoche.transaccion.dominio.servicio.ServicioTransaccion;
import lombok.RequiredArgsConstructor;
import com.alquilatucoche.transaccion.infraestructura.peticiones.PeticionCreacionTransaccion;

import jakarta.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class ImplementacionServicioContratacion implements ServicioContratacion{
	
	private final ServicioOferta servicioOferta;
	
	private final ServicioTransaccion servicioTransaccion;

	@Override
	@Transactional
	public ResultadoContratacion contratarOferta(PeticionContratacionOferta peticion) {
		
		Double precio = servicioOferta.obtenerPrecioPorContratacion(peticion.getId(), peticion.getDiasContratados());
		
		//pago y comprobación
		
		servicioOferta.establecerOfertaContratada(peticion.getId());
		
		PeticionCreacionTransaccion peticionTransaccion = PeticionCreacionTransaccion.builder()
				.precio(precio)
				.idOferta(peticion.getId())
				.idUsuario(peticion.getIdCliente())
				.build();
		
		TransaccionDTO transaccion = servicioTransaccion.crearTransaccion(peticionTransaccion);
		
		return ResultadoContratacion.builder()
				.oferta(servicioOferta.obtenerOferta(peticion.getId()))
				.transaccion(transaccion)
				.resultado("Exito")
				.build();
	}

	@Override
	@Transactional
	@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
	public void terminarContrato() {
		
		List<Long> idsOfertas = servicioTransaccion.obtenerIdOfertasCaducadas();
		
		servicioOferta.liberarOfertas(idsOfertas);
	}

}
