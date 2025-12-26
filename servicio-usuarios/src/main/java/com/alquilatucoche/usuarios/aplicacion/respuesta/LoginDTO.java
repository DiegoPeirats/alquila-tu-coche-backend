package com.alquilatucoche.usuarios.aplicacion.respuesta;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginDTO {
	
	private UsuarioDTO user;
	
	private String token;

}
