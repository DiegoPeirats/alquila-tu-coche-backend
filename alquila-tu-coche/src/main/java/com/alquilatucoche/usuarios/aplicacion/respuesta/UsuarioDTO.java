package com.alquilatucoche.usuarios.aplicacion.respuesta;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UsuarioDTO {
	
	private Long id;
	
	private String nombre;
	
	private String apellidos;
	
	private String genero;
	
	private String direccion;
	
	private String provincia;
	
	private String numeroDeCuenta;
	
	private String email;
	
	private String numeroTelefono;
	
	private Boolean propietario;

	private byte[] imagenPerfil;
	

}
