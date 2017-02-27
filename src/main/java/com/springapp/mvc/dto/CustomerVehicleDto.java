package com.springapp.mvc.dto;

import java.util.List;

/**
 * Created by Sehan Rathnayake on 17/02/25.
 */
public class CustomerVehicleDto {
    CustomerDto customer;
    List<VehicleLazyDto> vehicles;
    String customerName;
    int custId;

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
        this.customerName = customer.getCustName();
        this.custId = customer.getId();
    }

    public List<VehicleLazyDto> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleLazyDto> vehicles) {
        this.vehicles = vehicles;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }
}
