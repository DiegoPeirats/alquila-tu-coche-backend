package com.alquilatucoche.transaccion.dominio.servicio;

import java.util.List;

import com.alquilatucoche.transaccion.aplicacion.respuesta.TransaccionDTO;
import com.alquilatucoche.transaccion.infraestructura.peticiones.FiltroBusquedaTransacciones;
import com.alquilatucoche.transaccion.infraestructura.peticiones.PeticionCreacionTransaccion;

public interface ServicioTransaccion {
	
	TransaccionDTO crearTransaccion(PeticionCreacionTransaccion peticion);
	
	String eliminarTransaccion(Long id);
	
	List<TransaccionDTO> obtenerHistorialTransacciones(FiltroBusquedaTransacciones filtro);
	
	List<Long> obtenerIdOfertasCaducadas();

}
