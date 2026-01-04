package com.alquilatucoche.usuarios.infrastructura.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilatucoche.usuarios.dominio.entidad.Propietario;

public interface RepositorioPropietario extends JpaRepository<Propietario, Long> {
	
    Optional<Propietario> findByUsuarioId(Long usuarioId);
}
