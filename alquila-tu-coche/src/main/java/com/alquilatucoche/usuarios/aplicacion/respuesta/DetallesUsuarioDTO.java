package com.alquilatucoche.usuarios.aplicacion.respuesta;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;

@Builder
public class DetallesUsuarioDTO implements UserDetails{

	private static final long serialVersionUID = -6993881807220966822L;
	private Long id;
	private String email;
	private String password;
	private List<SimpleGrantedAuthority> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return roles;
	}
	
	public Long getId() {
        return id;
    }

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
