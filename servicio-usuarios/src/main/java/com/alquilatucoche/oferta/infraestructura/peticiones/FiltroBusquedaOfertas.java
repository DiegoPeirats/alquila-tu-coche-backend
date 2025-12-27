package com.alquilatucoche.oferta.infraestructura.peticiones;

import com.alquilatucoche.vehiculos.dominio.entidad.TipoVehiculo;

import lombok.Getter;

@Getter
public class FiltroBusquedaOfertas {
	
	private TipoVehiculo tipoVehiculo;
	
	private String provincia;
	
	private Double precioMinimo;
	
	private Double precioMaximo;

}
