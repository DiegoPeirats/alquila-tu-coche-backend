package com.alquilatucoche.oferta.aplicacion.respuesta;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultadoContratacion {
	
	private OfertaDTO oferta;
	
	private String resultado;

}
