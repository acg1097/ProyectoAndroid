package com.alexandre.proyectoandroid.beans;

/**
 * Created by Italo on 06/07/2017.
 */

public class VehiculoDTO extends ActivoDTO{

    public VehiculoDTO() {
        super();
    }

    private String placaVehiculo;

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
}
