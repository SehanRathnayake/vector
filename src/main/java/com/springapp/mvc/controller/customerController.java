package com.springapp.mvc.controller;

import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */
@Controller()
@RequestMapping("/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        Customer c=new Customer();
        c.setCustomerName("asdsasd");
        c.setCustomerEmail("asdsasd@asd.com");
        c.setCustomerPhone(071223231);
        c.setCustomerAddress("asdsasd");
        customerService.createJob(c);
        return "customer";
    }

}
