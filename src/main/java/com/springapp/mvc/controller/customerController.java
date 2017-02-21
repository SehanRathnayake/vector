package com.springapp.mvc.controller;

import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String customer(ModelMap model){
        return "Customer";
    }

    @RequestMapping(value = "/customerList", method = RequestMethod.POST)
    public @ResponseBody
    List<Customer> viewCustomers(ModelMap model){
        List<Customer> list = customerService.viewList();
        return list;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public @ResponseBody
    void addCustomer(@RequestBody String [] customerDetail){
//        Customer c=new Customer();
//        c.setCustomerName(data[0]);
//        c.setCustomerEmail("jsbc@nkvd.com");
//        c.setCustomerPhone("15361");
//        c.setCustomerAddress("skdhbviakjcs");
//        customerService.createJob(c);

        Customer customer = new Customer();
        customer.setCustomerid(Long.parseLong(customerDetail[0]));
        customer.setCustomerName(customerDetail[1]);
        customer.setCustomerAddress(customerDetail[2]);
        customer.setCustomerPhone(customerDetail[3]);
        customer.setCustomerEmail(customerDetail[4]);
        customerService.createJob(customer);
    }

    @RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
    public String removeCustomer(@RequestBody int removeId){
        int customerId = removeId;
        customerService.removeCustomer(customerId);
        return "customer";
    }

    @RequestMapping(value = "/viewCustomerDetail", method = RequestMethod.POST)
    public @ResponseBody
    CustomerDto viewCustomerDetails(@RequestBody String id){
        CustomerDto c = customerService.getSingleCustomer(1);;
        return c;
    }

    @RequestMapping(value = "/editCustomerDetail", method = RequestMethod.POST)
    public @ResponseBody
    void editCustomerDetails(@RequestBody String [] customerDetail){
        Customer customer = new Customer();
        customer.setCustomerid(Long.parseLong(customerDetail[0]));
        customer.setCustomerName(customerDetail[1]);
        customer.setCustomerAddress(customerDetail[2]);
        customer.setCustomerPhone(customerDetail[3]);
        customer.setCustomerEmail(customerDetail[4]);
    }
}
