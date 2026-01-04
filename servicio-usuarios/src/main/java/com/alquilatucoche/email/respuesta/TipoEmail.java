package com.alquilatucoche.email.respuesta;

public enum TipoEmail {

    VEHICULO_ALQUILADO(
        "VEHÍCULO ALQUILADO",
        "USTED HA ALQUILADO EL VEHÍCULO CON ID %s HASTA EL DÍA %s",
        "SU VEHÍCULO HA SIDO ALQUILADO POR EL USUARIO %s HASTA EL DÍA %s"
    ),

    ALQUILER_VENCIDO(
        "HA TERMINADO EL PERIODO DE ALQUILER",
        "USTED HA TERMINADO DE ALQUILAR EL VEHÍCULO %s",
        "USTED HA RECUPERADO LA POSESIÓN DEL VEHÍCULO %s"
    );

    private final String asunto;
    private final String mensajeCliente;
    private final String mensajePropietario;

    TipoEmail(String asunto, String mensajeCliente, String mensajePropietario) {
        this.asunto = asunto;
        this.mensajeCliente = mensajeCliente;
        this.mensajePropietario = mensajePropietario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getMensajeCliente(Object... args) {
        return String.format(mensajeCliente, args);
    }

    public String getMensajePropietario(Object... args) {
        return String.format(mensajePropietario, args);
    }
}