package com.alquilatucoche.valoracion.aplicacion.utiles;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alquilatucoche.valoracion.aplicacion.respuesta.ValoracionDTO;
import com.alquilatucoche.valoracion.dominio.entidad.Valoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionCrearValoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionModificarValoracion;

@Mapper(componentModel = "spring")
public interface ValoracionMapper {
	
	ValoracionDTO toDto(Valoracion reserva);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void actualizarValoracionDesdePeticion(PeticionModificarValoracion peticion, @MappingTarget Valoracion reserva);
	
	Valoracion crearValoracionDesdePeticion(PeticionCrearValoracion peticion);

}
