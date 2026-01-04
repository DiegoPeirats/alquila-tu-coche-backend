package com.alquilatucoche.pagos.dominio.entidad;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagos_enviados")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PagoEnviado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoPago tipoPago;
	
	private Long transaccionId;

	private String transferId;
	
	private String paymentIntentId;
	
	private Long importe;
	
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	
	@UpdateTimestamp
	private LocalDateTime fechaModificacion;


}
