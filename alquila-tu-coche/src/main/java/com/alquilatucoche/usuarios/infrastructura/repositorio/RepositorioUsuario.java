package com.alquilatucoche.usuarios.infrastructura.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquilatucoche.usuarios.dominio.entidad.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByEmail(String email);

}
