package com.springapp.mvc.service;

import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.dto.CustomerVehicleDto;
import com.springapp.mvc.model.Customer;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/15/2016.
 */
public interface CustomerService {
    public Customer createJob(CustomerDto customer);
    public List<Customer> viewList();
    public Customer getCustomer(String name);
    public void removeCustomer(int id);
    public CustomerDto getSingleCustomer(int id);
    public void update(CustomerDto customer);
    public List<CustomerVehicleDto> getCustomerVehicleList();
}
