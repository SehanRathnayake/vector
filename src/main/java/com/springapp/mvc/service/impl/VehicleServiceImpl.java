package com.springapp.mvc.service.impl;


import com.springapp.mvc.dto.ModelDto;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.dao.VehicleDao;
import com.springapp.mvc.model.Vehicle;
import com.springapp.mvc.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 2/21/2017.
 */

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public Vehicle createVehicle(VehicleDto vehicle) {
        return vehicleDao.createVehicle(vehicle);
    }

    @Override
    public List<Vehicle> viewList() {
        return vehicleDao.viewList();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<VehicleDto> getVehicle(int customerId) {
        return vehicleDao.getVehicle(customerId);
    }

    @Override
    public void removeVehicle(int id,int number) {
        vehicleDao.removeVehicle(id,number);
    }

    @Override
    public VehicleDto getSingleVehicle(int id) {
        return vehicleDao.getSingleVehicle(id);
    }

    @Override
    public void update(VehicleDto vehicle){
        vehicleDao.update(vehicle);
    }

    @Override
    public List<ModelDto> getModelList(){
        return vehicleDao.getModelList();
    }

}
