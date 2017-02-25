package com.springapp.mvc.controller;

import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.dto.CustomerVehicleDto;
import com.springapp.mvc.dto.DetailDto;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.service.CustomerService;
import com.springapp.mvc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

        List<CustomerVehicleDto> list=customerService.getCustomerVehicleList();

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
    int addCustomer(@RequestBody DetailDto data){

        System.out.println(data);
        DetailDto detail = data;
        CustomerDto customer = data.getCustomer();
        List<VehicleDto> vehicle = detail.getVehicleList();

        customerService.createJob(customer);
        ;
        for (VehicleDto veh:vehicle) {
            veh.setCustomer(customerService.getCustomer(customer.getCustName()).getCus_id());
            vehicleService.createVehicle(veh);
        }

        return 1;
    }

    @RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
    public String removeCustomer(@RequestBody int removeId){
        int customerId = removeId;
        customerService.removeCustomer(customerId);
        return "customer";
    }

    @RequestMapping(value = "/viewCustomerDetail", method = RequestMethod.POST)
    public @ResponseBody
    List<Object> viewCustomerDetails(@RequestBody int id){
        CustomerDto c = new CustomerDto();
        VehicleDto v = new VehicleDto();
        VehicleDto v1 = new VehicleDto();
        List<VehicleDto> vehicleList = new ArrayList<VehicleDto>();
        List<Object> list = new ArrayList<Object>();
        c = customerService.getSingleCustomer(id);
        vehicleList = vehicleService.getVehicle(id);
        list.add(c);
        list.addAll(vehicleList);

        return list;
    }

    @RequestMapping(value = "/editCustomerDetail", method = RequestMethod.POST)
    public @ResponseBody
    void editCustomerDetails(@RequestBody CustomerDto customer, VehicleDto vehicle){
//        Customer customer = new Customer();
        /*customer.setCustomerid(Integer.parseInt(customerDetail[0]));
        customer.setCustomerName(customerDetail[1]);
        customer.setCustomerAddress(customerDetail[2]);
        customer.setCustomerPhone(customerDetail[3]);
        customer.setCustomerEmail(customerDetail[4]);*/
    }
}
