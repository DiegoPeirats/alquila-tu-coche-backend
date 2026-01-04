package com.alquilatucoche.usuarios.aplicacion.respuesta;

import java.util.List;

import com.alquilatucoche.vehiculos.aplicacion.respuesta.VehiculoDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PropietarioDTO extends UsuarioDTO {
    private List<VehiculoDTO> vehiculos;
    private String cuenta;
}
