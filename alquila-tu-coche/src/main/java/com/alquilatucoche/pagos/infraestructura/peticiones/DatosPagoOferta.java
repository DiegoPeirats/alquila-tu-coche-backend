package com.alquilatucoche.pagos.infraestructura.peticiones;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DatosPagoOferta {
	
	private Long ofertaId;
	
	private Integer diasContratados;

}
