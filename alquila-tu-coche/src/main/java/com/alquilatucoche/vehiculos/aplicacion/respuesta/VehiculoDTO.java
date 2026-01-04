package com.alquilatucoche.vehiculos.aplicacion.respuesta;

import java.util.List;

import com.alquilatucoche.vehiculos.dominio.entidad.TipoVehiculo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehiculoDTO {
	
	private Long id;
	
	private TipoVehiculo tipo;
	
	private Long idPropietario;
	
	private String provincia;
	
	private List<byte[]> imagenes;

}
