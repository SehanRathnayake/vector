package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.VehicleDao;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Vehicle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/16/2016.
 */

@Repository
public class VehicleDaoImpl extends BaseJpaDaoImpl<Vehicle> implements VehicleDao{

    @Transactional

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return saveEntity(vehicle);
    }

    @Override
    public List<Vehicle> viewList() {
        String queryString = "SELECT v FROM Vehicle v";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        return query.getResultList();
    }

    @Override
    public List<Vehicle> getVehicle(long customerId) {
        String queryString = "SELECT v FROM Vehicle v WHERE v.customer= :id";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        query.setParameter("id", customerId);
        return null;
    }

    @Override
    public void removeVehicle(int customerId) {
        String queryString = "SELECT v FROM Vehicle v WHERE v.customer = :id";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        query.setParameter("id", customerId);
        Vehicle vehicle = query.getSingleResult();
        System.out.println(vehicle);
        deleteEntity(vehicle);
    }

    @Override
    public VehicleDto getSingleVehicle(int id) {
        return null;
    }
}
