package com.alquilatucoche.vehiculos.aplicacion.utiles;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.alquilatucoche.vehiculos.aplicacion.respuesta.VehiculoDTO;
import com.alquilatucoche.vehiculos.dominio.entidad.ImagenVehiculo;
import com.alquilatucoche.vehiculos.dominio.entidad.Vehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionAltaVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionModificacionVehiculo;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
	
	@Mapping(target = "imagenes", source = "imagenes", qualifiedByName = "mapImagenes")
	VehiculoDTO toDto(Vehiculo vehiculo);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void actualizarVehiculoDesdePeticion(PeticionModificacionVehiculo peticion, @MappingTarget Vehiculo vehiculo);
	
	Vehiculo crearVehiculoDesdePeticion(PeticionAltaVehiculo peticion);
	
	@Named("mapImagenes")
	default List<byte[]> mapImagenes(List<ImagenVehiculo> imagenes) {
	    if (imagenes == null) return null;
	    return imagenes.stream()
	                   .map(ImagenVehiculo::getDatos)
	                   .collect(Collectors.toList());
	}

}
