package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.VehicleDao;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Vehicle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class VehicleDaoImpl extends BaseJpaDaoImpl<Vehicle> implements VehicleDao{

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {
        return saveEntity(vehicle);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Vehicle> viewList() {
        String queryString = "SELECT v FROM Vehicle v";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        return query.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<VehicleDto> getVehicle(long customerId) {
        String queryString = "SELECT v FROM Vehicle v WHERE v.customer= :id";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        query.setParameter("id", ((int) customerId));
        List<Vehicle> vehicle = query.getResultList();
        List<VehicleDto> vehicleDto = new ArrayList<VehicleDto>();
        for (Vehicle veh:vehicle) {
            VehicleDto dto = new VehicleDto();
            dto.setManufactDate(veh.getManufactDate().toString());
            dto.setNumberPlate(veh.getNumberPlate());
            dto.setVehicleModelId(veh.getVehicleModelId());
            dto.setOdometer(veh.getOdometer());
            dto.setCustomer(veh.getCustomer());

            vehicleDto.add(dto);
        }
        return vehicleDto;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public VehicleDto getSingleVehicle(long id) {
//        Customer c = getEntity(Customer.class,id);
        String queryString = "SELECT v FROM Vehicle v WHERE v.customer = :id";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        query.setParameter("id", id);
        List<Vehicle> vehicle = query.getResultList();
//        List<Vehicle>
        VehicleDto vehicledto = new VehicleDto();
        /*vehicledto.setNumberPlate(vehicle.getNumberPlate());
        vehicledto.setVehicleModelId(vehicle.getVehicleModelId());
        vehicledto.setOdometer(vehicle.getOdometer());
        vehicledto.setManufactDate(vehicle.getManufactDate().toString());*/
        return vehicledto;
    }
}
