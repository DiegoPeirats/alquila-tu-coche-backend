package com.alquilatucoche.reserva.dominio.servicio;

import java.util.List;

import com.alquilatucoche.reserva.aplicacion.respuesta.ReservaDTO;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionCrearReserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionModificarReserva;

public interface ServicioReserva {
	
	ReservaDTO crearReserva (PeticionCrearReserva peticion);
	
	String eliminarReserva(Long id);
	
	ReservaDTO modificarReserva(PeticionModificarReserva peticion);
	
	ReservaDTO obtenerReserva(Long id);
	
	List<ReservaDTO> obtenerReservas(Long usuarioId);

}
