package com.alquilatucoche.valoracion.dominio.entidad;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alquilatucoche.oferta.dominio.entidad.Oferta;
import com.alquilatucoche.usuarios.dominio.entidad.Propietario;
import com.alquilatucoche.usuarios.dominio.entidad.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "valoraciones")
public class Valoracion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oferta_id", nullable = false)
	@JsonBackReference
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oferta_id", nullable = false)
	@JsonBackReference
	private Oferta oferta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "propietario_id", nullable = false)
	@JsonBackReference
	private Propietario propietario;
	
	private String mensaje;
	
	@Column(nullable = false, columnDefinition = "INTEGER CHECK (valoracion >= 1 AND valoracion <= 5)")
	private Integer valoracion;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime modifiedAt;

}
