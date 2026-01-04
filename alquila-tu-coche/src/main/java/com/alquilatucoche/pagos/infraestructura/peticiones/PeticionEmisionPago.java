package com.alquilatucoche.pagos.infraestructura.peticiones;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PeticionEmisionPago {
	
    private String propietarioStripeId;
    private Long transaccionId;
    private String paymentIntent;

}
