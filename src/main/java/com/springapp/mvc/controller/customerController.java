package com.springapp.mvc.controller;

import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.dto.CustomerVehicleDto;
import com.springapp.mvc.dto.DetailDto;
import com.springapp.mvc.dto.ModelDto;
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



        return "Customer";
    }

    @RequestMapping(value = "/customerList", method = RequestMethod.POST)
    public @ResponseBody
    List<Customer> viewCustomers(ModelMap model){
        List<Customer> list = customerService.viewList();
        return list;
    }

    @RequestMapping(value = "/customerVehicleList", method = RequestMethod.POST)
    public @ResponseBody
    List<CustomerVehicleDto> getCustomerVehicleList(ModelMap model){
        List<CustomerVehicleDto> list=customerService.getCustomerVehicleList();
        return list;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public @ResponseBody
    void addCustomer(@RequestBody DetailDto data){

        System.out.println(data);
        DetailDto detail = data;
        CustomerDto customer = data.getCustomer();
        List<VehicleDto> vehicle = data.getVehicleList();

        Customer newCust = customerService.createJob(customer);

        for (VehicleDto veh:vehicle) {
            veh.setCustomer(newCust.getCus_id());
            vehicleService.createVehicle(veh);
        }
    }

    @RequestMapping(value = "/removeCustomer", method = RequestMethod.POST)
    public String removeCustomer(@RequestBody int removeId){
        int customerId = removeId;
        customerService.removeCustomer(customerId);
        return "customer";
    }

    @RequestMapping(value = "/viewCustomerDetail", method = RequestMethod.POST)
    public @ResponseBody
    DetailDto viewCustomerDetails(@RequestBody int id){
        CustomerDto c = new CustomerDto();
        VehicleDto v = new VehicleDto();
        VehicleDto v1 = new VehicleDto();
        List<VehicleDto> vehicleList = new ArrayList<VehicleDto>();
        List<Object> list = new ArrayList<Object>();
        c = customerService.getSingleCustomer(id);
        vehicleList = vehicleService.getVehicle(id);
        DetailDto detail = new DetailDto();
        detail.setCustomer(c);
        detail.setVehicleList(vehicleList);
        return detail;
    }

    @RequestMapping(value = "/editCustomerDetail", method = RequestMethod.POST)
    public @ResponseBody
    void editCustomerDetails(@RequestBody DetailDto data){
        DetailDto detail = data;
        CustomerDto customer = data.getCustomer();
        List<VehicleDto> vehicle = data.getVehicleList();

        customerService.update(customer);

        for (VehicleDto veh:vehicle) {
            veh.setCustomer(customerService.getCustomer(customer.getCustName()).getCus_id());
            vehicleService.update(veh);
        }

    }

    @RequestMapping(value = "/modelList", method = RequestMethod.POST)
    @ResponseBody
    List<ModelDto> getModelList(ModelMap model){
        return vehicleService.getModelList();
    }
}
