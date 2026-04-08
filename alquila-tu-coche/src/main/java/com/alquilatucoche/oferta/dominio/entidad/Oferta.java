package com.alquilatucoche.oferta.dominio.entidad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alquilatucoche.reserva.dominio.entidad.Reserva;
import com.alquilatucoche.vehiculos.dominio.entidad.Vehiculo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ofertas")
public class Oferta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private EstadoOferta estado;

	@Column(nullable = false)
	private Double precioPorDia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehiculo_id", nullable = false)
	@JsonBackReference
	private Vehiculo vehiculo;
	
	@OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
	private List<Reserva> reservas = new ArrayList<>();;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

}
