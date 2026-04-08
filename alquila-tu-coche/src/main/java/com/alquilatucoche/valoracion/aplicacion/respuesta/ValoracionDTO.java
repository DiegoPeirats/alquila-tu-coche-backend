package com.alquilatucoche.valoracion.aplicacion.respuesta;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValoracionDTO {
	
	private Long id;
	
	private Long usuarioId;
	
	private Long ofertaId;
	
	private String mensaje;
	
	private Integer valoracion;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime modifiedAt;

}
