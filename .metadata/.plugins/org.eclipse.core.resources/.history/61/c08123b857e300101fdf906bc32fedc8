package com.alquilatucoche.vehiculos.aplicacion.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alquilatucoche.vehiculos.aplicacion.respuesta.VehiculoDTO;
import com.alquilatucoche.vehiculos.aplicacion.respuesta.excepciones.VehiculoNoEncontradoExcepcion;
import com.alquilatucoche.vehiculos.aplicacion.utiles.VehiculoMapper;
import com.alquilatucoche.vehiculos.dominio.entidad.ImagenVehiculo;
import com.alquilatucoche.vehiculos.dominio.entidad.Vehiculo;
import com.alquilatucoche.vehiculos.dominio.servicio.ServicioVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionAltaVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.peticiones.PeticionModificacionVehiculo;
import com.alquilatucoche.vehiculos.infraestructura.repositorio.RepositorioVehiculos;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplementacionServicioVehiculo implements ServicioVehiculo{
	
	private final RepositorioVehiculos repositorio;
	
	private final VehiculoMapper mapper;

	@Override
	public VehiculoDTO alta(PeticionAltaVehiculo peticion) {
		
		Vehiculo vehiculo = mapper.crearVehiculoDesdePeticion(peticion);
		
		vehiculo = repositorio.save(vehiculo);
		
		return mapper.toDto(vehiculo);
	}

	@Override
	public String baja(Long id) {
		repositorio.deleteById(id);
		return "Vehiculo eliminado con éxito";
	}

	@Override
	public VehiculoDTO modificacion(PeticionModificacionVehiculo peticion) {
		Vehiculo vehiculoEncontrado = repositorio.findById(peticion.getId())
				.orElseThrow(() -> new VehiculoNoEncontradoExcepcion());
		mapper.actualizarVehiculoDesdePeticion(peticion, vehiculoEncontrado);
		
		Vehiculo vehiculoActualizado = repositorio.save(vehiculoEncontrado);
		
		return mapper.toDto(vehiculoActualizado);
	}

	@Override
	public VehiculoDTO agregarImagen(Long idVehiculo, byte[] imagen) {
		
		Vehiculo vehiculoEncontrado = repositorio.findById(idVehiculo)
				.orElseThrow(() -> new VehiculoNoEncontradoExcepcion());
		
		ImagenVehiculo imagenVehiculo = ImagenVehiculo.builder()
				.vehiculo(vehiculoEncontrado)
				.datos(imagen)
				.build();
		vehiculoEncontrado.getImagenes().add(imagenVehiculo);
		
		repositorio.save(vehiculoEncontrado);
		
		return mapper.toDto(vehiculoEncontrado);
	}

	@Override
	public VehiculoDTO eliminarImagen(Long idVehiculo, Long idImagen) {
		
		Vehiculo vehiculoEncontrado = repositorio.findById(idVehiculo)
				.orElseThrow(() -> new VehiculoNoEncontradoExcepcion());
		
		vehiculoEncontrado.setImagenes(vehiculoEncontrado.getImagenes().stream()
				.filter(imagen -> imagen.getId() != idImagen)
				.collect(Collectors.toList()));
		
		repositorio.save(vehiculoEncontrado);
		return mapper.toDto(vehiculoEncontrado);
	}

	@Override
	public List<VehiculoDTO> obtenerVehiculosPropietario(Long idPropietario) {

		return repositorio.findAllByIdPropietario(idPropietario).stream()
				.map(vehiculo -> mapper.toDto(vehiculo))
				.collect(Collectors.toList());
	}

}
