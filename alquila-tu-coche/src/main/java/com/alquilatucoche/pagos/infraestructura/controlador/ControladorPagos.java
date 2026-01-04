package com.alquilatucoche.pagos.infraestructura.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alquilatucoche.pagos.dominio.servicio.ServicioPago;
import com.alquilatucoche.pagos.infraestructura.peticiones.DatosPagoOferta;
import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pagos")
public class ControladorPagos {

    private final ServicioPago servicioPago;

    @PostMapping("/recibirPago")
    public ResponseEntity<String> recibirPago(@RequestBody DatosPagoOferta datos) throws StripeException {
        return ResponseEntity.status(HttpStatus.OK).body(servicioPago.recibirPago(datos));
    }
}
