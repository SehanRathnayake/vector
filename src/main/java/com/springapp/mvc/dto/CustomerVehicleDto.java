package com.springapp.mvc.dto;

import java.util.List;

/**
 * Created by Sehan Rathnayake on 17/02/25.
 */
public class CustomerVehicleDto {
    CustomerDto customer;
    List<VehicleLazyDto> vehicles;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<VehicleLazyDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleLazyDto> vehicles) {
        this.vehicles = vehicles;
    }
}
