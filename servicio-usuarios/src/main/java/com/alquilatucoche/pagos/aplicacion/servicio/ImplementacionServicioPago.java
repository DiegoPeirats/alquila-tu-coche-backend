package com.alquilatucoche.pagos.aplicacion.servicio;

import org.springframework.stereotype.Service;

import com.alquilatucoche.oferta.dominio.servicio.ServicioOferta;
import com.alquilatucoche.pagos.aplicacion.respuesta.ImporteNoAdecuadoException;
import com.alquilatucoche.pagos.aplicacion.respuesta.PagoNoEncontradoExcepcion;
import com.alquilatucoche.pagos.dominio.entidad.PagoEnviado;
import com.alquilatucoche.pagos.dominio.entidad.PagoRecibido;
import com.alquilatucoche.pagos.dominio.entidad.TipoPago;
import com.alquilatucoche.pagos.dominio.servicio.ServicioPago;
import com.alquilatucoche.pagos.dominio.servicio.ServicioStripe;
import com.alquilatucoche.pagos.infraestructura.peticiones.DatosPagoOferta;
import com.alquilatucoche.pagos.infraestructura.peticiones.PeticionEmisionPago;
import com.alquilatucoche.pagos.infraestructura.peticiones.PeticionRecepcionPago;
import com.alquilatucoche.pagos.infraestructura.repositorio.RepositorioPagosEnviados;
import com.alquilatucoche.pagos.infraestructura.repositorio.RepositorioPagosRecibidos;
import com.alquilatucoche.usuarios.dominio.servicio.ServicioUsuario;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplementacionServicioPago implements ServicioPago{
	
	private final ServicioStripe servicioStripe;
	
	private final RepositorioPagosRecibidos repositorioPagosRecibidos;
	
	private final RepositorioPagosEnviados repositorioPagosEnviados;
	
	private final ServicioOferta servicioOferta;
	
	private final ServicioUsuario servicioUsuario;

	@Override
	public String recibirPago(DatosPagoOferta datos) throws StripeException {

		String idStripeCliente = servicioStripe.crearCliente(servicioUsuario.miInformacion());
		
		PeticionRecepcionPago peticion = PeticionRecepcionPago.builder()
				.idCliente(servicioUsuario.miInformacion().getId().toString())
				.idStripeCliente(idStripeCliente)
				.ofertaId(datos.getOfertaId())
				.precio(servicioOferta.obtenerPrecioPorContratacion(datos.getOfertaId(), datos.getDiasContratados()))
				.urlExito("https://tuapp.com/onboarding/completed")
				.urlFallo("https://tuapp.com/onboarding/retry")
				.build();
		
		Session session = Session.create(servicioStripe.crearSesion(peticion));
		return session.getUrl(); // url para redirigir al usuario a Stripe Checkout y que pague en la pasarela de pago
	}

	@Override
	@Transactional
	public String enviarPago(PeticionEmisionPago peticion) throws StripeException {
		
		if (servicioStripe.comprobacionCuentaValida(peticion.getPropietarioStripeId())) {
			
			PagoRecibido pagoRecibido = repositorioPagosRecibidos.findByTransaccionId(peticion.getTransaccionId())
					.orElseThrow(() -> new PagoNoEncontradoExcepcion());
			
			Long precioAPagar = (long) (pagoRecibido.getImporte() / 1.1);
			
			String transferId = servicioStripe.transfirACuenta(peticion, precioAPagar);
			
	        PagoEnviado pagoEnviado = PagoEnviado.builder()
	        		.tipoPago(TipoPago.ENVIADO)
	        		.transaccionId(peticion.getTransaccionId())
	        		.transferId(transferId)
	        		.paymentIntentId(pagoRecibido.getPaymentIntentId())
	        		.importe(precioAPagar) // se envia sin la comisión del 10%
	        		.build();

	        repositorioPagosEnviados.save(pagoEnviado);
			
			return "Resultado Exitoso";
		}
			
		return "El usuario destino no tiene una cuenta válida para recibir el pago";
		
	}
	
	//falta un metodo para devolver dinero al cliente en caso necesario
	
	@Override
    public String confirmarPago(String sessionId, Long transaccionId, Long precio) throws StripeException {

        // 1️⃣ Recuperar la sesión desde Stripe
        Session session = Session.retrieve(sessionId);

        // 2️⃣ Obtener el payment_intent generado por Stripe
        String paymentIntentId = session.getPaymentIntent();

        Long importe = session.getAmountTotal();
        
        if (importe != precio) throw new ImporteNoAdecuadoException();

        PagoRecibido pago = PagoRecibido.builder()
        		.transaccionId(transaccionId)
        		.paymentIntentId(paymentIntentId)
        		.sessionId(sessionId)
        		.importe(importe)
        		.build();

        repositorioPagosRecibidos.save(pago);

        return paymentIntentId;
    }

}
