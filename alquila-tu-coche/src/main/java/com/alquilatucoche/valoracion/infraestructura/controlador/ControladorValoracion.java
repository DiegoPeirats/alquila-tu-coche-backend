package com.alquilatucoche.valoracion.infraestructura.controlador;

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

import com.alquilatucoche.valoracion.aplicacion.respuesta.ValoracionDTO;
import com.alquilatucoche.valoracion.dominio.servicio.ServicioValoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionCrearValoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionModificarValoracion;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/valoraciones")
@RequiredArgsConstructor
public class ControladorValoracion {
	
	private final ServicioValoracion servicio;
	
	@PostMapping("/crearValoracion")
	public ResponseEntity<ValoracionDTO> crearReserva(@RequestBody PeticionCrearValoracion peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crearValoracion(peticion));
	}
	
	@PutMapping("/modificarValoracion")
	public ResponseEntity<ValoracionDTO> modificarReserva(@RequestBody PeticionModificarValoracion peticion){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.modificarValoracion(peticion));
	}

	@DeleteMapping("/eliminarValoracion")
	public ResponseEntity<String> eliminarReserva(@RequestBody Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.eliminarValoracion(id));
	}
	
	@GetMapping("/obtenerValoracion/{id}")
	public ResponseEntity<ValoracionDTO> obtenerValoracion(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.obtenerValoracion(id));
	}
	
	@GetMapping("/obtenerValoracionesEmitidas/{id}")
	public ResponseEntity<List<ValoracionDTO>> obtenerValoracionesEmitidas(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.obtenerValoracionesEmitidas(id));
	}
	
	@GetMapping("/obtenerValoracionesRecibidas/{id}")
	public ResponseEntity<List<ValoracionDTO>> obtenerValoracionesRecibidas(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.obtenerValoracionesRecibidas(id));
	}

}