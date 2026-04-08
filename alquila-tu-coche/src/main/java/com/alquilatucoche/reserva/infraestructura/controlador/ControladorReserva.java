package com.alquilatucoche.reserva.infraestructura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilatucoche.reserva.aplicacion.respuesta.ReservaDTO;
import com.alquilatucoche.reserva.dominio.servicio.ServicioReserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionCrearReserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionModificarReserva;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/reservas")
@RequiredArgsConstructor
public class ControladorReserva {
	
	private final ServicioReserva servicio;
	
	@PostMapping("/crearReserva")
	public ResponseEntity<ReservaDTO> crearReserva(@RequestBody PeticionCrearReserva peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crearReserva(peticion));
	}
	
	
	@PutMapping("/modificarReserva")
	public ResponseEntity<ReservaDTO> modificarReserva(@RequestBody PeticionModificarReserva peticion){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.modificarReserva(peticion));
	}

	
	@DeleteMapping("/eliminarReserva")
	public ResponseEntity<String> eliminarReserva(@RequestBody Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.eliminarReserva(id));
	}

	
	@PostMapping("/obtenerReservas")
	public ResponseEntity<List<ReservaDTO>> obtenerReservas(@RequestBody Long usuarioId){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.obtenerReservas(usuarioId));
	}
	
	@GetMapping("/obtenerReserva/{id}")
	public ResponseEntity<ReservaDTO> obtenerReserva(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.obtenerReserva(id));
	}

}
