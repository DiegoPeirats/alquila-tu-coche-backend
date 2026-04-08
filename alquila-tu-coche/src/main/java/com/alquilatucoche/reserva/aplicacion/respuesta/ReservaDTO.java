package com.alquilatucoche.reserva.aplicacion.respuesta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.alquilatucoche.oferta.dominio.entidad.EstadoOferta;
import com.alquilatucoche.pagos.dominio.entidad.PagoRecibido;
import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaDTO {
	
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoReserva estado;
	
	private PagoRecibido pago;

	private LocalDate fechaInicio;
	
	private LocalDate fechaFin;
	
	private Long ofertaId;
	
	@CreationTimestamp
	private LocalDateTime fechaCreacion;

}
