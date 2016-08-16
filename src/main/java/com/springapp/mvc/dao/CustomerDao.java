package com.springapp.mvc.dao;
import com.springapp.mvc.model.Customer;

import java.util.List;


/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */
public interface CustomerDao {

    public List<Customer> getCustomer(String customer_name);
    public Customer createJob(Customer customer);
}
