package com.alquilatucoche.oferta.aplicacion.utiles;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alquilatucoche.oferta.aplicacion.respuesta.OfertaDTO;
import com.alquilatucoche.oferta.dominio.entidad.Oferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionCreacionOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionModificarOferta;

@Mapper(componentModel = "spring")
public interface OfertaMapper {
	
	OfertaDTO toDto(Oferta vehiculo);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void actualizarOfertaDesdePeticion(PeticionModificarOferta peticion, @MappingTarget Oferta oferta);
	
	Oferta crearOfertaDesdePeticion(PeticionCreacionOferta peticion);

}
