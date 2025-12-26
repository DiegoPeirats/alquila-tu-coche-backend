package com.alquilatucoche.usuarios.infrastructura.peticiones;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeticionAltaUsuario {
	
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotBlank(message = "Los apellidos son obligatorios")
	private String apellidos;

	@NotBlank(message = "El género es obligatorio")
	private String genero;

	@NotBlank(message = "La dirección es obligatoria")
	private String direccion;

	@NotBlank(message = "La provincia es obligatoria")
	private String provincia;

	private String numeroDeCuenta;

	@NotBlank(message = "La dirección de email es obligatoria")
	@Email(message = "El email no es válido")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	private String password;

	@NotBlank(message = "El teléfono es obligatorio")
	private String numeroTelefono;

	private Boolean propietario;

	private String stripeId;
	
	private byte[] imagenPerfil;

}
