package com.alquilatucoche.usuarios.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import com.alquilatucoche.valoracion.dominio.entidad.Valoracion;
import com.alquilatucoche.vehiculos.dominio.entidad.Vehiculo;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "propietarios")
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(nullable = false)
    private String numeroDeCuenta;
    
    @OneToMany(mappedBy = "propietario") 
    @JsonManagedReference
    private List<Valoracion> valoracionesRecibidas = new ArrayList<>();
    
    @OneToMany(mappedBy = "propietario") 
    @JsonManagedReference
    private List<Vehiculo> vehiculos = new ArrayList<>();

    @Column(nullable = false)
    private byte[] imagenContrato;

    @CreationTimestamp
    private LocalDateTime fechaRegistro;
}