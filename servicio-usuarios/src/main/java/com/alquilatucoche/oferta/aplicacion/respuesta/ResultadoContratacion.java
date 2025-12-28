package com.alquilatucoche.oferta.aplicacion.respuesta;

import com.alquilatucoche.transaccion.aplicacion.respuesta.TransaccionDTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultadoContratacion {
	
	private OfertaDTO oferta;
	
	private TransaccionDTO transaccion;
	
	private String resultado;

}
