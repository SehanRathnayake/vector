package com.springapp.mvc.service;

import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Vehicle;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/17/2016.
 */

public interface VehicleService {
    public Vehicle createVehicle(Vehicle vehicle);
    public List<Vehicle> viewList();
    public List<VehicleDto> getVehicle(long custoemrId);
    public void removeVehicle(int id);
    public VehicleDto getSingleVehicle(int id);
}
