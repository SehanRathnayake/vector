package com.springapp.mvc.controller;

import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.model.Vehicle;
import com.springapp.mvc.service.CustomerService;
import com.springapp.mvc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */
@Controller()
@RequestMapping("/")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customer(ModelMap model){
        return "customer";
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
    List<Object> viewCustomerDetails(@RequestBody long id){
        CustomerDto c = new CustomerDto();
        VehicleDto v = new VehicleDto();
        VehicleDto v1 = new VehicleDto();
        List<VehicleDto> vehicleList = new ArrayList<VehicleDto>();

//        c = customerService.getSingleCustomer(((int) id));
//        v = vehicleService.getVehicle(id);
        c.setId(id);
        c.setCustName("Heshani");
        c.setCustEmail("heshanisamarasekara@gmail.com");
        c.setCustAddress("Deshani,Kirnida");
        c.setCustTp("0712189826");
        List<Object> list = new ArrayList<Object>();
        list.add(c);
        for (VehicleDto vehicle:vehicleList) {
            list.add(vehicle);
        }
        v.setManufactDate("2001-01-01");
        v.setCustomer(((int) id));
        v.setNumberPlate("123456");
        v.setOdometer(123);
        v.setVehicleModelId(1);

        v1.setManufactDate("2001-01-01");
        v1.setCustomer(((int) id));
        v1.setNumberPlate("123456");
        v1.setOdometer(123);
        v1.setVehicleModelId(1);

        list.add(v);
        list.add(v1);

        return list;
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
