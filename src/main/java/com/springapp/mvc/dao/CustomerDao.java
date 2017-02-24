package com.springapp.mvc.dao;
import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.model.Customer;

import java.util.List;


/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */
public interface CustomerDao {
    public List<Customer> getCustomer(String customer_name);
    public List<Customer> getCustomerList();
    public Customer createJob(Customer customer);
    public void removeCustomer(long id);
    public CustomerDto getSingleCustomer(long id);
}
