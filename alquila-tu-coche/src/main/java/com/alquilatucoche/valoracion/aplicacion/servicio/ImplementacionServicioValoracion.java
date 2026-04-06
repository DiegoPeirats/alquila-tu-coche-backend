package com.alquilatucoche.valoracion.aplicacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquilatucoche.oferta.dominio.servicio.ServicioOferta;
import com.alquilatucoche.usuarios.dominio.servicio.ServicioUsuario;
import com.alquilatucoche.valoracion.aplicacion.respuesta.ValoracionDTO;
import com.alquilatucoche.valoracion.aplicacion.respuesta.ValoracionNoEncontradaExcepcion;
import com.alquilatucoche.valoracion.aplicacion.utiles.ValoracionMapper;
import com.alquilatucoche.valoracion.dominio.servicio.ServicioValoracion;
import com.alquilatucoche.valoracion.dominio.entidad.Valoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionCrearValoracion;
import com.alquilatucoche.valoracion.infraestructura.peticiones.PeticionModificarValoracion;
import com.alquilatucoche.valoracion.infraestructura.repositorio.RepositorioValoracion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplementacionServicioValoracion implements ServicioValoracion{
	
	private final RepositorioValoracion repositorio;
	
	private final ValoracionMapper mapper;
	
	private final ServicioUsuario servicioUsuario;
	
	private final ServicioOferta servicioOferta;
	
	@Override
	public ValoracionDTO crearValoracion(PeticionCrearValoracion peticion) {
		
		Valoracion valoracion = mapper.crearValoracionDesdePeticion(peticion);
		
		valoracion.setUsuario(servicioUsuario.miInformacionUsuario());
		
		repositorio.save(valoracion);
		
		return mapper.toDto(valoracion);
	}

	@Override
	public ValoracionDTO modificarValoracion(PeticionModificarValoracion peticion) {
		
		Valoracion valoracion = encontrarValoracion(peticion.getId());
		
		mapper.actualizarValoracionDesdePeticion(peticion, valoracion);
		
		repositorio.save(valoracion);
		
		return mapper.toDto(valoracion);
	}

	private Valoracion encontrarValoracion(Long id) {
		
		return repositorio.findById(id)
				.orElseThrow(() -> new ValoracionNoEncontradaExcepcion());
	}

	@Override
	public String eliminarValoracion(Long id) {
		
		encontrarValoracion(id);
		
		repositorio.deleteById(id);
		
		return "Valoración eliminada con éxito";
	}

	@Override
	public List<ValoracionDTO> obtenerValoracionesRecibidas(Long propietarioId) {
		
		List<Long> ofertasDelPropietario = servicioOferta.obtenerIdOfertas(propietarioId);
		
		return ofertasDelPropietario.stream()
		        .map(idOferta -> repositorio.findAllByIdOferta(idOferta)) 
		        .flatMap(List::stream) 
		        .map(mapper::toDto) 
		        .toList();
	}

	@Override
	public List<ValoracionDTO> obtenerValoracionesEmitidas(Long usuarioId) {
		
		return repositorio.findAllByIdCliente(usuarioId).stream()
				.map(val -> mapper.toDto(val))
				.collect(Collectors.toList());
	}

	@Override
	public ValoracionDTO obtenerValoracion(Long id) {
		Valoracion valoracion = encontrarValoracion(id);
		return mapper.toDto(valoracion);
	}

}
