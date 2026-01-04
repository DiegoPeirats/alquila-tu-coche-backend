package com.alquilatucoche.email;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilatucoche.email.servicio.ServicioEmail;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("ap1/email")
@RequiredArgsConstructor
public class ControladorEmail {
	
	private final ServicioEmail servicioEmail;

}
