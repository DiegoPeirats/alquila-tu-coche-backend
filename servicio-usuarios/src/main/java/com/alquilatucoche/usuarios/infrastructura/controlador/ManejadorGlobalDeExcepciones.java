package com.alquilatucoche.usuarios.infrastructura.controlador;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alquilatucoche.oferta.aplicacion.respuesta.excepcion.OfertaNoEncontradaExcepcion;
import com.alquilatucoche.usuarios.aplicacion.respuesta.excepciones.UsuarioNoEncontradoExcepcion;
import com.alquilatucoche.vehiculos.aplicacion.respuesta.excepciones.VehiculoNoEncontradoExcepcion;

@ControllerAdvice
public class ManejadorGlobalDeExcepciones {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> genericException(Exception ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	@ExceptionHandler(UsuarioNoEncontradoExcepcion.class)
	public ResponseEntity<String> usuarioNotFound(UsuarioNoEncontradoExcepcion ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(VehiculoNoEncontradoExcepcion.class)
	public ResponseEntity<String> vehiculoNotFound(VehiculoNoEncontradoExcepcion ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(VehiculoNoEncontradoExcepcion.class)
	public ResponseEntity<String> ofertaNotFound(OfertaNoEncontradaExcepcion ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> argumentNotValid(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
