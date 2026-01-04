package com.alquilatucoche.oferta.aplicacion.respuesta;

import com.alquilatucoche.oferta.dominio.entidad.EstadoOferta;

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

}
