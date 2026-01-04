package com.alquilatucoche.oferta.infraestructura.peticiones;

import com.alquilatucoche.pagos.infraestructura.peticiones.DatosPagoOferta;

import lombok.Getter;

@Getter
public class PeticionContratacionOferta {
	
	private String sessionId;
	
	private DatosPagoOferta datos;

}
