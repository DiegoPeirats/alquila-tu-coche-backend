package com.alquilatucoche.pagos.infraestructura.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilatucoche.pagos.dominio.entidad.PagoRecibido;

public interface RepositorioPagosRecibidos extends JpaRepository<PagoRecibido, Long>{
	
	Optional<PagoRecibido> findByTransaccionId(Long transaccionId);

}
