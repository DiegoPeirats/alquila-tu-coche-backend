package com.alquilatucoche.transaccion.aplicacion.respuesta.excepcion;

public class TransaccionNoEncontradaExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4100617794729007603L;

	public TransaccionNoEncontradaExcepcion() {
		super("Transaccion no encontrada");
	}
	
	

}
