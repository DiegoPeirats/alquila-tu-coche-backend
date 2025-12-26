package com.alquilatucoche.usuarios.aplicacion.respuesta.excepciones;

public class UsuarioNoEncontradoExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNoEncontradoExcepcion() {
		super("Usuario no encontrado");
	}
	
	

}
