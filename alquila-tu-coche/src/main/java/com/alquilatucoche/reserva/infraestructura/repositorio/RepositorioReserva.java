package com.alquilatucoche.reserva.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;
import com.alquilatucoche.reserva.dominio.entidad.Reserva;

@Repository
public interface RepositorioReserva extends JpaRepository<Reserva, Long>{

	List<Reserva> findByEstado(EstadoReserva estado);
}
