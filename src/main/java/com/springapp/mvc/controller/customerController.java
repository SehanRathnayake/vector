package com.springapp.mvc.controller;

import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */
@Controller()
@RequestMapping("/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public @ResponseBody
    List<Customer> viewCustomers(){
        List<Customer> list = customerService.viewList();
        return list;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public @ResponseBody
    String viewCustomer(@RequestParam(value = "array")String[]array){
        Customer c=new Customer();
        c.setCustomerName("ksncj");
        c.setCustomerEmail("jsbc@nkvd.com");
        c.setCustomerPhone(2365451);
        c.setCustomerAddress("skdhbviakjcs");
        customerService.createJob(c);
        return "addCust";
    }

    @RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
    public String removeCustomer(ModelMap model){
        return "remove";
    }

    @RequestMapping(value = "/customerDetail", method = RequestMethod.GET)
    public String viewCustomerDetails(){
        return "customerDetail";
    }

}
