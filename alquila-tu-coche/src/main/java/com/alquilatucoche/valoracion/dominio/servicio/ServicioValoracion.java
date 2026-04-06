package com.alquilatucoche.valoracion.dominio.servicio;

import java.util.List;

import com.alquilatucoche.valoracion.aplicacion.respuesta.ValoracionDTO;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionCrearValoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionModificarValoracion;

public interface ServicioValoracion {
	
	ValoracionDTO crearValoracion(PeticionCrearValoracion peticion);
	
	ValoracionDTO modificarValoracion(PeticionModificarValoracion peticion);
	
	String eliminarValoracion(Long id);
	
	List<ValoracionDTO> obtenerValoracionesRecibidas(Long propietarioId);
	
	List<ValoracionDTO> obtenerValoracionesEmitidas(Long usuarioId);
	
	ValoracionDTO obtenerValoracion(Long id);

}
