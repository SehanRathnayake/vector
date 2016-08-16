package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.VehicleDao;
import com.springapp.mvc.model.Vehicle;

/**
 * Created by Heshani Samarasekara on 8/16/2016.
 */
public class VehicleDaoImpl implements VehicleDao{
    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicle;
    }
}
