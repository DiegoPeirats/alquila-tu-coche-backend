package com.alquilatucoche.email.servicio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioEmail {
	
	private final JavaMailSender mailSender;
	
    @Value("${spring.mail.username}")
    private String username;

    public void enviarCorreo(String para, String asunto, String mensaje) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(para);
        email.setSubject(asunto);
        email.setText(mensaje);
        email.setFrom(username);

        mailSender.send(email);
    }

}