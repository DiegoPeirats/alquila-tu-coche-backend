package com.alquilatucoche.usuarios.infrastructura.seguridad;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alquilatucoche.usuarios.aplicacion.respuesta.DetallesUsuarioDTO;
import com.alquilatucoche.usuarios.aplicacion.respuesta.excepciones.UsuarioNoEncontradoExcepcion;
import com.alquilatucoche.usuarios.dominio.entidad.Usuario;
import com.alquilatucoche.usuarios.infrastructura.repositorio.RepositorioUsuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final RepositorioUsuario repositorio;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repositorio.findByEmail(username)
				.orElseThrow(() -> new UsuarioNoEncontradoExcepcion());
		
		return DetallesUsuarioDTO.builder()
				.id(usuario.getId())
				.email(username)
				.password(usuario.getPassword())
				.roles(List.of(new SimpleGrantedAuthority(usuario.getRole().toString())))
				.build();
	}

}
