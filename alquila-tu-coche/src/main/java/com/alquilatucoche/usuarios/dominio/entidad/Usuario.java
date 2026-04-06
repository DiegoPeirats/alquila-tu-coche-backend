package com.alquilatucoche.usuarios.dominio.entidad;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.alquilatucoche.reserva.dominio.entidad.Reserva;
import com.alquilatucoche.valoracion.dominio.entidad.Valoracion;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellidos;
    private String genero;
    private String direccion;
    private String provincia;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String numeroTelefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;   

    @Lob
    private byte[] imagenPerfil;
    
    @OneToMany(mappedBy = "usuario") 
    @JsonManagedReference
    private List<Valoracion> valoracionesEmitidas = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario") 
    @JsonManagedReference
    private List<Reserva> reservas = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaModificacion;

    @OneToOne(mappedBy = "usuario",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Propietario propietario;

    // Método para mantener consistencia bidireccional
    public void asignarPropietario(Propietario p) {
        this.propietario = p;
        p.setUsuario(this);
    }
}
