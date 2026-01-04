package com.alquilatucoche.usuarios.aplicacion.servicio;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alquilatucoche.email.servicio.ServicioEmail;
import com.alquilatucoche.usuarios.aplicacion.respuesta.DetallesUsuarioDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.LoginDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.PropietarioDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.UsuarioDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.excepciones.UsuarioNoEncontradoExcepcion;
import com.alquilatucoche.usuarios.aplicacion.utiles.UsuarioMapper;
import com.alquilatucoche.usuarios.dominio.entidad.Role;
import com.alquilatucoche.usuarios.dominio.entidad.Usuario;
import com.alquilatucoche.usuarios.dominio.servicio.ServicioUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionAltaUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionLogin;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionModificacionUsuario;
import com.alquilatucoche.usuarios.infrastructura.peticiones.PeticionRegistroPropietario;
import com.alquilatucoche.usuarios.infrastructura.repositorio.RepositorioUsuario;
import com.alquilatucoche.usuarios.infrastructura.seguridad.ProveedorJwtToken;
import com.alquilatucoche.vehiculos.dominio.servicio.ServicioVehiculo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplementacionServicioUsuario implements ServicioUsuario{
	
	private final RepositorioUsuario repositorio;
	
	private final UsuarioMapper mapper;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final ProveedorJwtToken proveedorToken;
	
	private final ServicioEmail servicioEmail;
	
	private final ServicioVehiculo servicioVehiculos;

	@Override
	public UsuarioDTO altaUsuario(PeticionAltaUsuario peticion) {
		
		peticion.setPropietario(false);
		peticion.setPassword(passwordEncoder.encode(peticion.getPassword()));
		
		Usuario usuario = mapper.crearUsuarioDesdePeticion(peticion);
		
		usuario.setRole(Role.ROLE_USER);
		
		repositorio.save(usuario);
		
		return mapper.toUsuarioDTO(usuario);
	}

	@Override
	public String bajaUsuario(Long id) {
		try {
			repositorio.deleteById(id);
			return "Usuario dado de baja con éxito";
		}catch(Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public UsuarioDTO modificacionUsuario(PeticionModificacionUsuario peticion) {
		
	    Usuario usuario = repositorio.findById(peticion.getId())
	            .orElseThrow(UsuarioNoEncontradoExcepcion::new);

	    peticion.setId(null);

	    mapper.actualizarUsuarioDesdePeticion(peticion, usuario);

	    Usuario usuarioActualizado = repositorio.save(usuario);
	    
	    return comprobarSiPropietario(usuarioActualizado);
	}

	@Override
	public UsuarioDTO busquedaUsuario(Long id) {
		return repositorio.findById(id)
				.map(usuario -> mapper.toUsuarioDTO(usuario))
				.orElseThrow(UsuarioNoEncontradoExcepcion::new);
	}

	@Override
	public List<UsuarioDTO> recuperacionListaUsuarios() {
		return repositorio.findAll().stream()
				.map(usuario -> mapper.toUsuarioDTO(usuario))
				.toList();
	}

	@Override
	public LoginDTO login(PeticionLogin peticion) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(peticion.getEmail(), peticion.getPassword()));
		
		Usuario usuario = repositorio.findByEmail(peticion.getEmail())
				.orElseThrow(UsuarioNoEncontradoExcepcion::new);
		
		return LoginDTO.builder()
				.user(mapper.toUsuarioDTO(usuario))
				.token(proveedorToken.generateToken(auth))
				.build();
	}

	@Override
	public UsuarioDTO miInformacion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		DetallesUsuarioDTO detallesUsuario = (DetallesUsuarioDTO) auth.getPrincipal();
		
		return repositorio.findById(detallesUsuario.getId())
				.map(usuario -> comprobarSiPropietario(usuario))
				.orElseThrow(UsuarioNoEncontradoExcepcion::new);
	}

	@Override
	public String registrarComoPropietario(PeticionRegistroPropietario peticion) {
		servicioEmail.enviarCorreo("manuemanue123456789@gmail.com", "Solicitud de propietario", peticion.toString());
		return "Solicitud enviada con éxito";
	}
	
	private UsuarioDTO comprobarSiPropietario(Usuario usuario) {
		if (usuario.getRole().equals(Role.ROLE_PROPIETARIO)) {
	    	
	    	PropietarioDTO propietario = mapper.toPropietarioDTO(usuario);
	    	
	    	propietario.setVehiculos(servicioVehiculos.obtenerVehiculosPropietario(propietario.getId()));
	    	
	    	return propietario;
	    	
	    }

	    return mapper.toUsuarioDTO(usuario);
	}

}
