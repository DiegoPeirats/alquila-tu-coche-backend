package com.alquilatucoche.usuarios.infrastructura.peticiones;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionModificacionUsuario {
	
	private Long id;
	
	private String nombre;
	
	private String apellidos;
	
	private String genero;
	
	private String direccion;
	
	private String provincia;
	
	private String email;
	
	private String password;
	
	private String numeroTelefono;
	
	private byte[] imagenPerfil;

}
