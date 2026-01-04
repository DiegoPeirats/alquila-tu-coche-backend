package com.alquilatucoche.vehiculos.infraestructura.controlador;

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

import com.alquilatucoche.vehiculos.aplicacion.respuesta.VehiculoDTO;
import com.alquilatucoche.vehiculos.dominio.servicio.ServicioVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionAgregarImagen;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionAltaVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionEliminarImagen;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionModificacionVehiculo;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class ControladorVehiculos {
	
	private final ServicioVehiculo servicio;
	
	
	@PostMapping("/alta")
	public ResponseEntity<VehiculoDTO> alta(@RequestBody PeticionAltaVehiculo peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.alta(peticion));
	}
	
	@PutMapping("/modificacion")
	public ResponseEntity<VehiculoDTO> modificacion(@RequestBody PeticionModificacionVehiculo peticion){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.modificacion(peticion));
	}
	
	@DeleteMapping("/baja/{id}")
	public ResponseEntity<String> baja(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.baja(id));
	}
	
	@GetMapping("/vehiculosUsuario/{id}")
	public ResponseEntity<List<VehiculoDTO>> vehiculosUsuario(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(servicio.obtenerVehiculosPropietario(id));
	}	
	
	@PostMapping("/agregarImagen")
	public ResponseEntity<VehiculoDTO> agregarImagen(@RequestBody PeticionAgregarImagen peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.agregarImagen(peticion.getIdVehiculo(), peticion.getImagen()));
	}	
	
	@PostMapping("/eliminarImagen")
	public ResponseEntity<VehiculoDTO> eliminarImagen(@RequestBody PeticionEliminarImagen peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.eliminarImagen(peticion.getIdVehiculo(), peticion.getIdImagen()));
	}

}
