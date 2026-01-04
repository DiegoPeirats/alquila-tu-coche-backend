package com.alquilatucoche.vehiculos.dominio.servicio;

import java.util.List;

import com.alquilatucoche.vehiculos.aplicacion.respuesta.VehiculoDTO;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionAltaVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionModificacionVehiculo;

public interface ServicioVehiculo {
	
	VehiculoDTO alta(PeticionAltaVehiculo peticion);
	
	String baja(Long id);
	
	VehiculoDTO modificacion(PeticionModificacionVehiculo peticion);
	
	VehiculoDTO agregarImagen(Long idVehiculo, byte[] imagen);
	
	VehiculoDTO eliminarImagen(Long idVehiculo, Long idImagen);
	
	List<VehiculoDTO> obtenerVehiculosPropietario(Long idPropietario);
	
	VehiculoDTO encontrarVehiculo(Long id);

}
