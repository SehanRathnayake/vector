package com.springapp.mvc.dto;

import java.util.List;

/**
 * @author MaN
 *         on 2/23/2017.
 */
public class CustVehicleTempDto {
    private String name;
    private List<String> vehicles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }
}
