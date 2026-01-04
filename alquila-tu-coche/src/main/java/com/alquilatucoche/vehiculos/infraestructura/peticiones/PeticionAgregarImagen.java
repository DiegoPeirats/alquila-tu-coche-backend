package com.alquilatucoche.vehiculos.infraestructura.peticiones;

import lombok.Getter;

@Getter
public class PeticionAgregarImagen {
	
	private Long idVehiculo;
	private byte[] imagen;

}
