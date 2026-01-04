package com.alquilatucoche.pagos.aplicacion.respuesta;

public class PagoNoEncontradoExcepcion extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3079554036857902251L;

	public PagoNoEncontradoExcepcion() {
		super("No se ha encontrado el pago");
	}

	
}
