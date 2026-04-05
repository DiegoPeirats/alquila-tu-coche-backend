package com.alquilatucoche.reserva.aplicacion.respuesta;

public class ReservaNoEncontradaException extends RuntimeException{

	public ReservaNoEncontradaException() {
		super("Reserva no encontrada");
	}

}
