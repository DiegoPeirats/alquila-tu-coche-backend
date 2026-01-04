package com.alquilatucoche.usuarios.infrastructura.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilatucoche.usuarios.aplicacion.respuesta.LoginDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.UsuarioDTO;
import com.alquilatucoche.usuarios.aplicacion.servicio.ImplementacionServicioUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionAltaUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionLogin;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionModificacionUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionRegistroPropietario;
import com.stripe.exception.StripeException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ControladorUsuarios {
	
	private final ImplementacionServicioUsuario servicio;
	
	
	@PostMapping("/public/alta")
	public ResponseEntity<UsuarioDTO> alta(@Valid @RequestBody PeticionAltaUsuario peticion){
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.altaUsuario(peticion));
		
	}
	
	@PostMapping("/public/login")
	public ResponseEntity<LoginDTO> login(@RequestBody PeticionLogin peticion){
		return ResponseEntity.status(HttpStatus.OK).body(servicio.login(peticion));
	}

	@PreAuthorize("hasRole('ADMIN') or #id == principal.id")
	@PutMapping("/actualizarUsuario")
	public ResponseEntity<UsuarioDTO> modificacion(@RequestBody PeticionModificacionUsuario peticion){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.modificacionUsuario(peticion));
		
	}
	

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
	@DeleteMapping("/baja/{id}")
	public ResponseEntity<String> baja(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.bajaUsuario(id));
		
	}
	

    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
	@GetMapping("/encontrarUsuario/{id}")
	public ResponseEntity<UsuarioDTO> encontrarUsuario(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(servicio.busquedaUsuario(id));
		
	}
	

	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> usuarios(){
		return ResponseEntity.status(HttpStatus.FOUND).body(servicio.recuperacionListaUsuarios());
		
	}
	
    @GetMapping("/miInformacion")
    public ResponseEntity<UsuarioDTO> myInformation(){
    	return ResponseEntity.status(HttpStatus.FOUND).body(servicio.miInformacion());
    }
    
    @PostMapping("/public/registrarPropietario")
    public ResponseEntity<String> registrarPropietario(@RequestBody PeticionRegistroPropietario peticion) throws StripeException{
    	return ResponseEntity.status(HttpStatus.OK).body(servicio.registrarComoPropietario(peticion));
    }

}
