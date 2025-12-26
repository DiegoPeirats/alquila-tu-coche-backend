package com.alquilatucoche.vehiculos.infraestructura.peticiones;

import com.alquilatucoche.vehiculos.dominio.entidad.TipoVehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PeticionAltaVehiculo {
	
	private TipoVehiculo tipo;
	
	private String provincia;
	
	private String matricula;

}
