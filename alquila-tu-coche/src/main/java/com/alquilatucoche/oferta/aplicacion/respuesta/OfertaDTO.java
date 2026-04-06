package com.alquilatucoche.oferta.aplicacion.respuesta;

import java.util.List;

import com.alquilatucoche.oferta.dominio.entidad.EstadoOferta;
import com.alquilatucoche.reserva.dominio.entidad.Reserva;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OfertaDTO {
	
	private EstadoOferta estado;
	
	private Double precioPorDia;
	
	private Long idVehiculo;
	
	private List<Reserva> reservas;

}
