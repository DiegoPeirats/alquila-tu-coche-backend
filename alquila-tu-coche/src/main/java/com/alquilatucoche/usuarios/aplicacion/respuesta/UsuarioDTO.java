package com.alquilatucoche.usuarios.aplicacion.respuesta;


import java.util.List;

import com.alquilatucoche.reserva.dominio.entidad.Reserva;
import com.alquilatucoche.valoracion.dominio.entidad.Valoracion;

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
	
	private String email;
	
	private String numeroTelefono;

	private byte[] imagenPerfil;
	
	private List<Reserva> reservas;
	 
	private List<Valoracion> valoraciones;
	

}
