package com.alquilatucoche.oferta.infraestructura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilatucoche.oferta.aplicacion.respuesta.OfertaDTO;
import com.alquilatucoche.oferta.aplicacion.respuesta.ResultadoContratacion;
import com.alquilatucoche.oferta.dominio.servicio.ServicioContratacion;
import com.alquilatucoche.oferta.dominio.servicio.ServicioOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.FiltroBusquedaOfertas;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionContratacionOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionCreacionOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionModificarOferta;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/ofertas")
@RequiredArgsConstructor
public class ControladorOfertas {
	
	private final ServicioOferta servicioOferta;
	
	private final ServicioContratacion servicioContratacion;
	
	@PostMapping("/crearOferta")
	public ResponseEntity<OfertaDTO> crearOferta(PeticionCreacionOferta peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicioOferta.crearOferta(peticion));
	}
	
	
	@PutMapping("/modificarOferta")
	public ResponseEntity<OfertaDTO> modificarOferta(PeticionModificarOferta peticion){
		return ResponseEntity.status(HttpStatus.OK).body(servicioOferta.modificarOferta(peticion));
	}

	
	@DeleteMapping("/eliminarOferta")
	public ResponseEntity<String> eliminarOferta(Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicioOferta.eliminarOferta(id));
	}

	
	@PostMapping("/obtenerOfertas")
	public ResponseEntity<List<OfertaDTO>> obtenerOfertas(FiltroBusquedaOfertas filtro){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicioOferta.obtenerOfertas(filtro));
	}

	
	@PostMapping("/contratarOferta")
	public ResponseEntity<ResultadoContratacion> contratarOferta(PeticionContratacionOferta peticion) throws StripeException{
		return ResponseEntity.status(HttpStatus.OK).body(servicioContratacion.contratarOferta(peticion));
	}
	

}
