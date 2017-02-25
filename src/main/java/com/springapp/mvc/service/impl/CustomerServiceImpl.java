package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.dao.VehicleDao;
import com.springapp.mvc.dao.VehicleModelDao;
import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.dto.CustomerVehicleDto;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.dto.VehicleLazyDto;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.model.Model;
import com.springapp.mvc.model.Vehicle;
import com.springapp.mvc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/15/2016.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private VehicleModelDao vehicleModelDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer createJob(CustomerDto customer) {
        return customerDao.createCustomer(customer);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> viewList() {
        return customerDao.getCustomerList();
    }

    public Customer getCustomer(String name) {
        return customerDao.getCustomer(name);
    }

    public void removeCustomer(int id) {
        customerDao.removeCustomer(id);
    }

    public CustomerDto getSingleCustomer(int id) {
        return customerDao.getSingleCustomer(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CustomerVehicleDto> getCustomerVehicleList() {
        List<Customer> customerList = customerDao.getCustomerList();
        List<CustomerVehicleDto> customerVehicleDtoList = new LinkedList<CustomerVehicleDto>();
        for (Customer customer : customerList) {
            CustomerDto c = new CustomerDto();
            c.setId(customer.getCus_id());
            c.setCustName(customer.getCus_name());
            List<VehicleDto> vehicles = vehicleDao.getVehicle(customer.getCus_id());
            List<VehicleLazyDto> vehicleLazyDtoList = new LinkedList<VehicleLazyDto>();
            for (VehicleDto vehicle : vehicles) {
                VehicleLazyDto v = new VehicleLazyDto();
                v.setNumberPlate(vehicle.getNumberPlate());
                v.setVehicleId(vehicle.getVehicleId());
                Model model = vehicleModelDao.getModel(vehicle.getVehicleModelId());
                v.setType(model.getVehicleType());
                v.setManufacturer(model.getManufacturer());
                vehicleLazyDtoList.add(v);
            }
            CustomerVehicleDto customerVehicleDto = new CustomerVehicleDto();
            customerVehicleDto.setCustomer(c);
            customerVehicleDto.setVehicles(vehicleLazyDtoList);

            customerVehicleDtoList.add(customerVehicleDto);
        }
        return customerVehicleDtoList;
    }

}
