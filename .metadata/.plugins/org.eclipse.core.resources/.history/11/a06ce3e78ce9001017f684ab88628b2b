package com.alquilatucoche.usuarios.aplicacion.utiles;

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
    private String from;

    public void enviarCorreo(String para, String asunto, String mensaje) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(para);
        email.setSubject(asunto);
        email.setText(mensaje);
        email.setFrom(from);

        mailSender.send(email);
    }

}
