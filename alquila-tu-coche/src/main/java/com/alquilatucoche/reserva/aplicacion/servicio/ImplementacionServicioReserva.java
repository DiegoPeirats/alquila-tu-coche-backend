package com.alquilatucoche.reserva.aplicacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquilatucoche.pagos.aplicacion.respuesta.PagoNoEncontradoExcepcion;
import com.alquilatucoche.pagos.aplicacion.servicio.ImplementacionServicioPago;
import com.alquilatucoche.pagos.dominio.entidad.PagoRecibido;
import com.alquilatucoche.pagos.infraestructura.repositorio.RepositorioPagosRecibidos;
import com.alquilatucoche.reserva.aplicacion.respuesta.ReservaDTO;
import com.alquilatucoche.reserva.aplicacion.respuesta.ReservaNoEncontradaException;
import com.alquilatucoche.reserva.aplicacion.utiles.ReservaMapper;
import com.alquilatucoche.reserva.dominio.entidad.EstadoReserva;
import com.alquilatucoche.reserva.dominio.entidad.Reserva;
import com.alquilatucoche.reserva.dominio.servicio.ServicioReserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionCrearReserva;
import com.alquilatucoche.reserva.infraestructura.peticiones.PeticionModificarReserva;
import com.alquilatucoche.reserva.infraestructura.repositorio.RepositorioReserva;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplementacionServicioReserva implements ServicioReserva{
	
	private final RepositorioReserva repositorio;

	private final ReservaMapper mapper;
	
	private final RepositorioPagosRecibidos repoPago;
	
	@Override
	public ReservaDTO crearReserva(PeticionCrearReserva peticion) {
		
		Reserva reserva = mapper.crearReservaDesdePeticion(peticion);
		
		reserva.setEstado(EstadoReserva.PENDIENTE);
		
		repositorio.save(reserva);
		
		return mapearReserva(reserva);
	}

	@Override
	public String eliminarReserva(Long id) {
		repositorio.deleteById(id);
		return "Reserva eliminada con éxito";
	}

	@Override
	public ReservaDTO modificarReserva(PeticionModificarReserva peticion) {
		
		Reserva reserva = getReserva(peticion.getId());
		
		mapper.actualizarReservaDesdePeticion(peticion, reserva);
		
		repositorio.save(reserva);
		
		return mapearReserva(reserva);
	}

	@Override
	public ReservaDTO obtenerReserva(Long id) {
		Reserva reserva = getReserva(id);
		return mapper.toDto(reserva);
		
	}
	
	private Reserva getReserva(Long id) {
		return repositorio.findById(id)
				.orElseThrow(() -> new ReservaNoEncontradaException());
	}

	@Override
	public List<ReservaDTO> obtenerReservas(Long usuarioId) {
		
		return repositorio.findAll().stream()
				.filter(res -> {
					
					Long pagoId = res.getPagoId();
					
					PagoRecibido pago = repoPago.findById(pagoId)
							.orElseThrow(() -> new ReservaNoEncontradaException());
					
					Long userId = pago.getUsuarioId();
					
					return userId == usuarioId;
				})
				.map(res -> mapearReserva(res))
				.collect(Collectors.toList());
	}

	@Override
	public ReservaDTO cambiarEstado(EstadoReserva estado, Long idReserva) {
		Reserva res = getReserva(idReserva);
		res.setEstado(estado);
		repositorio.save(res);
		
		return mapearReserva(res);
	}

	@Override
	public List<Long> obtenerIdOfertasCaducadas() {
		return repositorio.findByEstado(EstadoReserva.ACABADA).stream()
				.map(res -> res.getOferta().getId())
				.collect(Collectors.toList());
	}
	
	private ReservaDTO mapearReserva(Reserva reserva) {
		ReservaDTO dto = mapper.toDto(reserva);
		dto.setOfertaId(reserva.getOferta().getId());
		dto.setPago(repoPago.findById(reserva.getPagoId()).orElseThrow(()-> new PagoNoEncontradoExcepcion()));
		return dto;
	}


}
