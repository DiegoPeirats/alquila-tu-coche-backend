package com.alquilatucoche.oferta.infraestructura.peticiones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeticionCreacionOferta {
	
	private Double precioPorDia;
	
	private Long idVehiculo;

}
