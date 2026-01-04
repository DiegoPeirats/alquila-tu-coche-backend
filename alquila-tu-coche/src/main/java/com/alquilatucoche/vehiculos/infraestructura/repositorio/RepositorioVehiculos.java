package com.alquilatucoche.vehiculos.infraestructura.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alquilatucoche.vehiculos.dominio.entidad.Vehiculo;

public interface RepositorioVehiculos extends JpaRepository<Vehiculo, Long>{
	
	List<Vehiculo> findAllByIdPropietario(Long idPropietario);

}
