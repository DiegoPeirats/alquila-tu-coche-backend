package com.alquilatucoche.usuarios.infrastructura.seguridad;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FiltroAutenticacionJwt extends OncePerRequestFilter {
	
	private final ProveedorJwtToken proveedorToken;
	
	private final CustomUserDetailsService servicioDetallesUsuario;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = extraerToken(request);
		
		if (StringUtils.hasText(token) && proveedorToken.validarToken(token)) {
			
			String username = proveedorToken.getUsername(token);
			
			UserDetails userDetails = servicioDetallesUsuario.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			SecurityContextHolder.getContext().setAuthentication(auth);
			
		}
		
		filterChain.doFilter(request, response);
	}

	private String extraerToken(HttpServletRequest request) {

		String bearer = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
			return bearer.substring(7);
		
		return null;
	}
	
	

}
