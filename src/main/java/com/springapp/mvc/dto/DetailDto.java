package com.springapp.mvc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Heshani Samarasekara on 2/25/2017.
 */
public class DetailDto implements Serializable {
    private CustomerDto customer;
    private List<VehicleDto> vehicleList;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public List<VehicleDto> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<VehicleDto> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
