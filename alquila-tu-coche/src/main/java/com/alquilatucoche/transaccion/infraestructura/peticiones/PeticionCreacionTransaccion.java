package com.alquilatucoche.transaccion.infraestructura.peticiones;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PeticionCreacionTransaccion {
	
	private Long idUsuario;
	
	private Long idOferta;
	
	private Double precio;

}
