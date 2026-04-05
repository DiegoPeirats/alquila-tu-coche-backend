package com.alquilatucoche.reserva.aplicacion.utiles;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alquilatucoche.reserva.aplicacion.respuesta.ReservaDTO;
import com.alquilatucoche.reserva.dominio.entidad.Reserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionCrearReserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionModificarReserva;

@Mapper(componentModel = "spring")
public interface ReservaMapper {
	
	ReservaDTO toDto(Reserva reserva);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void actualizarReservaDesdePeticion(PeticionModificarReserva peticion, @MappingTarget Reserva reserva);
	
	Reserva crearReservaDesdePeticion(PeticionCrearReserva peticion);

}
