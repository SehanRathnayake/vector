package com.springapp.mvc.dto;

import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 17/02/25.
 */
public class VehicleLazyDto implements Serializable {
    private int vehicleId;
    private String numberPlate;
    private String type;
    private String manufacturer;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }


    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
