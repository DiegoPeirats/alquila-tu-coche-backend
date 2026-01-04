package com.alquilatucoche.pagos.infraestructura.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilatucoche.pagos.dominio.entidad.PagoEnviado;

public interface RepositorioPagosEnviados extends JpaRepository<PagoEnviado, Long>{

}
