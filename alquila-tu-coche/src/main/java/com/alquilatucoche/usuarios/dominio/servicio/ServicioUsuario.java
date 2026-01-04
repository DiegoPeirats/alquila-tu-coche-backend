package com.alquilatucoche.usuarios.dominio.servicio;

import java.util.List;

import com.alquilatucoche.usuarios.aplicacion.respuesta.LoginDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.UsuarioDTO;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionAltaUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionLogin;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionModificacionUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionRegistroPropietario;

public interface ServicioUsuario {
	
	UsuarioDTO altaUsuario(PeticionAltaUsuario peticion);
	
	String bajaUsuario(Long id);
	
	UsuarioDTO modificacionUsuario(PeticionModificacionUsuario peticion);
	
	UsuarioDTO busquedaUsuario(Long id);
	
	List<UsuarioDTO> recuperacionListaUsuarios();
	
	LoginDTO login(PeticionLogin peticion);
	
	UsuarioDTO miInformacion();
	
	String registrarComoPropietario(PeticionRegistroPropietario peticion);

}
