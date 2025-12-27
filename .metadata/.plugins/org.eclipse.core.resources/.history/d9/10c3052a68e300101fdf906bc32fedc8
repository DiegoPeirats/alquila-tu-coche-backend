package com.alquilatucoche.oferta.aplicacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquilatucoche.oferta.aplicacion.respuesta.OfertaDTO;
import com.alquilatucoche.oferta.aplicacion.respuesta.ResultadoContratacion;
import com.alquilatucoche.oferta.aplicacion.respuesta.excepcion.OfertaNoEncontradaExcepcion;
import com.alquilatucoche.oferta.aplicacion.utiles.OfertaMapper;
import com.alquilatucoche.oferta.dominio.entidad.EstadoOferta;
import com.alquilatucoche.oferta.dominio.entidad.Oferta;
import com.alquilatucoche.oferta.dominio.servicio.ServicioOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.FiltroBusquedaOfertas;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionContratacionOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionCreacionOferta;
import com.alquilatucoche.oferta.infraestructura.peticiones.PeticionModificarOferta;
import com.alquilatucoche.oferta.infraestructura.repositorio.RepositorioOfertas;
import com.alquilatucoche.vehiculos.aplicacion.respuesta.VehiculoDTO;
import com.alquilatucoche.vehiculos.dominio.servicio.ServicioVehiculo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplementacionServicioOferta implements ServicioOferta{
	
	private final RepositorioOfertas repositorio;
	
	private final OfertaMapper mapper;
	
	private final ServicioVehiculo servicioVehiculo;

	@Override
	public OfertaDTO crearOferta(PeticionCreacionOferta peticion) {

		Oferta oferta = mapper.crearOfertaDesdePeticion(peticion);
		
		oferta.setEstado(EstadoOferta.DISPONIBLE);
		
		repositorio.save(oferta);
		
		return mapper.toDto(oferta);
	}

	@Override
	public String eliminarOferta(Long id) {

		repositorio.deleteById(id);
		
		return "Oferta eliminada con éxito";
	}

	@Override
	public OfertaDTO modificarOferta(PeticionModificarOferta peticion) {
		
		Oferta oferta = repositorio.findById(peticion.getId())
				.orElseThrow(() -> new OfertaNoEncontradaExcepcion());
		
		mapper.actualizarOfertaDesdePeticion(peticion, oferta);
		
		repositorio.save(oferta);
		
		return mapper.toDto(oferta);
	}

	@Override
	public List<OfertaDTO> obtenerOfertas(FiltroBusquedaOfertas filtro) {
		
		return repositorio.findAll().stream()
				.filter(oferta -> {
					Long idVehiculo = oferta.getIdVehiculo();
					
					VehiculoDTO vehiculo = servicioVehiculo.encontrarVehiculo(idVehiculo);
					
					boolean tipoCorrecto = true;
					
					if (filtro.getTipoVehiculo() != null) tipoCorrecto = vehiculo.getTipo().equals(filtro.getTipoVehiculo());
					
					boolean provinciaCorrecta = true;
					
					if (filtro.getProvincia() != null && !filtro.getProvincia().isBlank()) provinciaCorrecta = vehiculo.getProvincia().equalsIgnoreCase(filtro.getProvincia());
					
					boolean precioMinimoCorrecto = true;
					
					if (filtro.getPrecioMinimo() != null) precioMinimoCorrecto = filtro.getPrecioMinimo() >= oferta.getPrecioPorDia();
					
					boolean precioMaximoCorrecto = true;
					
					if (filtro.getPrecioMaximo() != null) precioMinimoCorrecto = filtro.getPrecioMaximo() <= oferta.getPrecioPorDia();
					
					return tipoCorrecto && provinciaCorrecta && precioMaximoCorrecto && precioMinimoCorrecto;
				})
				.map(oferta -> mapper.toDto(oferta))
				.collect(Collectors.toList());
	}

	@Override
	public ResultadoContratacion contratarOferta(PeticionContratacionOferta peticion) {
		// TODO Auto-generated method stub
		return null;
	}

}
