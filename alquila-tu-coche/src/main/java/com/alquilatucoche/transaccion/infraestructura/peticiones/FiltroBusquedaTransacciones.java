package com.alquilatucoche.transaccion.infraestructura.peticiones;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class FiltroBusquedaTransacciones {
	
	private Long idPropietario;
	
	private Long idCliente;
	
	private Long idVehiculo;
	
	private LocalDate desdeCuando;

}
