package com.alquilatucoche.reserva.infraestructura.peticiones;

import java.time.LocalDate;

import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;

import lombok.Getter;

@Getter
public class PeticionModificarReserva {
	
	private Long id;

	private EstadoReserva estadoReserva;

	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;

}
