package com.alquilatucoche.pagos.aplicacion.respuesta;

public class ImporteNoAdecuadoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8407849562010718105L;

	public ImporteNoAdecuadoException() {
		super("El importe no es correcto");
	}
	
	

}
