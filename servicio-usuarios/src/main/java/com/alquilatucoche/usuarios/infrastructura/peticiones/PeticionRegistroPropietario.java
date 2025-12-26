package com.alquilatucoche.usuarios.infrastructura.peticiones;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class PeticionRegistroPropietario {
	
	@NotBlank(message = "El contrato es obligatorio")
	private byte[] imagenContrato;

	@NotBlank(message = "El número de cuenta es obligatorio")
	private String numeroDeCuenta;
	
	

}
