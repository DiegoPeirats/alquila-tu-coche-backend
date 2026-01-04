package com.alquilatucoche.pagos.dominio.servicio;

import com.alquilatucoche.pagos.infraestructura.peticiones.PeticionEmisionPago;
import com.alquilatucoche.pagos.infraestructura.peticiones.PeticionRecepcionPago;
import com.alquilatucoche.usuarios.aplicacion.respuesta.UsuarioDTO;
import com.stripe.exception.StripeException;

import com.stripe.param.checkout.SessionCreateParams;

public interface ServicioStripe {
	
	String crearCliente(UsuarioDTO usuario) throws StripeException;
	
	//creacion de cuenta Stripe para el propietario
	String crearCuentaConexion(UsuarioDTO usuario) throws StripeException;

	//redirigir al propietario a la web de stripe para que registre su numero de cuenta
	String crearOnboardingLink(String accountId) throws StripeException;

	boolean comprobacionCuentaValida(String idCuenta) throws StripeException;

	SessionCreateParams crearSesion(PeticionRecepcionPago peticion);

	String transfirACuenta(PeticionEmisionPago peticion, Long cantidad) throws StripeException;

}
