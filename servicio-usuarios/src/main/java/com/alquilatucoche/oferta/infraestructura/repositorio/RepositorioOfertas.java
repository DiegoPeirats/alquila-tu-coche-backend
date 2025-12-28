package com.alquilatucoche.oferta.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alquilatucoche.oferta.dominio.entidad.EstadoOferta;
import com.alquilatucoche.oferta.dominio.entidad.Oferta;


public interface RepositorioOfertas extends JpaRepository<Oferta, Long>{
	
	List<Oferta> findAllByEstado(EstadoOferta estado);
	
	@Modifying
	@Query("""
	  update Oferta o
	  set o.estado = 'DISPONIBLE'
	  where o.id in :ids
		and o.estado = 'CONTRATADA'
	""")
	void liberarOfertas(@Param("ids") List<Long> ids);

}
