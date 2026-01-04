package com.alquilatucoche.transaccion.infraestructura.repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alquilatucoche.transaccion.dominio.entidad.Transaccion;

public interface RepositorioTransaccion extends JpaRepository<Transaccion, Long>{
	
	@Query("""
			  select t from Transaccion t
			  where t.fechaCreacion + t.diasContratados < :ahora
			""")
			List<Transaccion> findContratosVencidos(@Param("ahora") LocalDateTime ahora);
	
	Optional<Transaccion> findFirstByIdClienteOrderByFechaCreacionDesc(Long idCliente);


}
