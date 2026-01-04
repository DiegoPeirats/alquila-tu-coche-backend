package com.alquilatucoche.transaccion.aplicacion.utiles;

import org.mapstruct.Mapper;

import com.alquilatucoche.transaccion.aplicacion.respuesta.TransaccionDTO;
import com.alquilatucoche.transaccion.dominio.entidad.Transaccion;
import com.alquilatucoche.transaccion.infraestructura.peticiones.PeticionCreacionTransaccion;

@Mapper(componentModel = "spring")
public interface TransaccionMapper {
	
	TransaccionDTO toDto(Transaccion vehiculo);
	
	Transaccion crearTransaccionDesdePeticion(PeticionCreacionTransaccion peticion);
	

}
