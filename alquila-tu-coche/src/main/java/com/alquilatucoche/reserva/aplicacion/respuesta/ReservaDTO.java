package com.alquilatucoche.reserva.aplicacion.respuesta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.alquilatucoche.pagos.dominio.entidad.PagoRecibido;
import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class ReservaDTO {
	
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoReserva estadoReserva;
	
	private PagoRecibido pago;

	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	@CreationTimestamp
	private LocalDateTime fechaCreacion;

}
