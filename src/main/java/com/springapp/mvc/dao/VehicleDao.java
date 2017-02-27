package com.springapp.mvc.dao;

import com.springapp.mvc.dto.ModelDto;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Model;
import com.springapp.mvc.model.Vehicle;

import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/16/2016.
 */

public interface VehicleDao {
    public Vehicle createVehicle(VehicleDto vehicle);
    public List<Vehicle> viewList();
    public List<VehicleDto> getVehicle(int customerId);
    public void removeVehicle(int id,int number);
    public VehicleDto getSingleVehicle(int id);
    public List<ModelDto> getModelList();
    public void update(VehicleDto vehicle);
}
