package com.alquilatucoche.transaccion.aplicacion.respuesta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TransaccionDTO {
	
	private Long id;
	
	private Long idUsuario;
	
	private Long idOferta;
	
	private Integer diasContratados;

}
