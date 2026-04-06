package com.alquilatucoche.reserva.infraestructura.peticiones;

import java.time.LocalDate;

import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;

import lombok.Getter;

@Getter
public class PeticionCrearReserva {

	private EstadoReserva estadoReserva;
	
	private Long pagoId;

	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private Long ofertaId;

}
