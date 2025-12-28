package com.alquilatucoche.transaccion.infraestructura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilatucoche.transaccion.aplicacion.respuesta.TransaccionDTO;
import com.alquilatucoche.transaccion.dominio.servicio.ServicioTransaccion;
import com.alquilatucoche.transaccion.infraestructura.peticiones.FiltroBusquedaTransacciones;
import com.alquilatucoche.transaccion.infraestructura.peticiones.PeticionCreacionTransaccion;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/transacciones")
@RequiredArgsConstructor
public class ControladorTransaccion {
	
	private final ServicioTransaccion servicio;
	
	@PostMapping("/crearTransaccion")
	public ResponseEntity<TransaccionDTO> crearTransaccion(PeticionCreacionTransaccion peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crearTransaccion(peticion));
	}
	
	@DeleteMapping("/eliminarTransaccion")
	public ResponseEntity<String> eliminarTransaccion(Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.eliminarTransaccion(id));
	}
	
	@GetMapping("/historialTransacciones")
	public ResponseEntity<List<TransaccionDTO>> obtenerHistorial(FiltroBusquedaTransacciones filtro){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.obtenerHistorialTransacciones(filtro));
	}

}
