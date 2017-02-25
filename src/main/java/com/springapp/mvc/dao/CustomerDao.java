package com.springapp.mvc.dao;
import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.model.Customer;

import java.util.List;


/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */
public interface CustomerDao {
    public Customer getCustomer(String customer_name);
    public List<Customer> getCustomerList();
    public Customer createCustomer(CustomerDto customer);
    public void removeCustomer(int id);
    public CustomerDto getSingleCustomer(int id);
}
