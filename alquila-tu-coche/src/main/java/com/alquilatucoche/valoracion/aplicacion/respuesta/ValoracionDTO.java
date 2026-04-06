package com.alquilatucoche.valoracion.aplicacion.respuesta;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ValoracionDTO {
	
	private Long id;
	
	private Long idCliente;
	
	private Long idOferta;
	
	private String mensaje;
	
	private Integer valoracion;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;

}
