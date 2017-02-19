package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/15/2016.
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer createJob(Customer customer){
        return customerDao.createJob(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> viewList(){
        return customerDao.getCustomerList();
    }

    public List<Customer> getCustomer(String name){
        return customerDao.getCustomer(name);
    }
}
