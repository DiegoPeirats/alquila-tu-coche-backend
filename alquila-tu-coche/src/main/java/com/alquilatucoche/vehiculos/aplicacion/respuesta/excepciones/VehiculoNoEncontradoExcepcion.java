package com.alquilatucoche.vehiculos.aplicacion.respuesta.excepciones;

public class VehiculoNoEncontradoExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehiculoNoEncontradoExcepcion() {
		super("Vehiculo no encontrado");
	}
	
	

}
