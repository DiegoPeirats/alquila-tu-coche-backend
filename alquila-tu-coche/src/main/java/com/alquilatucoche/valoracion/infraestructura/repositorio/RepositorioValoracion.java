package com.alquilatucoche.valoracion.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquilatucoche.valoracion.dominio.entidad.Valoracion;

@Repository
public interface RepositorioValoracion extends JpaRepository<Valoracion, Long>{
	
	List<Valoracion> findAllByUsuario_Id(Long idCliente);

	List<Valoracion> findAllByOferta_Id(Long idOferta);
}
