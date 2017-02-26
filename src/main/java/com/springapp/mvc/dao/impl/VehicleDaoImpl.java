package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.VehicleDao;
import com.springapp.mvc.dto.ModelDto;
import com.springapp.mvc.dto.VehicleDto;
import com.springapp.mvc.model.Model;
import com.springapp.mvc.model.Vehicle;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class VehicleDaoImpl extends BaseJpaDaoImpl<Vehicle> implements VehicleDao{

    @Override
    public Vehicle createVehicle(VehicleDto vehicle) {
        Vehicle veh = new Vehicle();
        veh.setCustomer(vehicle.getCustomer());
        veh.setOdometer(vehicle.getOdometer());
        veh.setNumberPlate(vehicle.getNumberPlate());
        veh.setVehicleModelId(vehicle.getVehicleModelId());
        veh.setManufactDate(vehicle.getManufactDate());
        return saveEntity(veh);
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
    public List<VehicleDto> getVehicle(int customerId) {

        String queryString = "SELECT v FROM Vehicle v  WHERE v.customer= :id";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        query.setParameter("id", customerId);
        List<Vehicle> vehicle = query.getResultList();
        List<VehicleDto> vehicleDto = new ArrayList<VehicleDto>();
        for (Vehicle veh:vehicle) {
            VehicleDto dto = new VehicleDto();
            dto.setManufactDate(veh.getManufactDate());
            dto.setNumberPlate(veh.getNumberPlate());
            dto.setVehicleModelId(veh.getVehicleModelId());
            dto.setOdometer(veh.getOdometer());
            dto.setVehicleId(veh.getVehicleId());
//            dto.setCustomer(veh.getCustomer());

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
    public VehicleDto getSingleVehicle(int id) {
        String queryString = "SELECT v FROM Vehicle v WHERE v.customer = :id";
        TypedQuery<Vehicle> query = this.entityManager.createQuery(queryString, Vehicle.class);
        query.setParameter("id", id);
        List<Vehicle> vehicle = query.getResultList();
        VehicleDto vehicledto = new VehicleDto();
        /*vehicledto.setNumberPlate(vehicle.getNumberPlate());
        vehicledto.setVehicleModelId(vehicle.getVehicleModelId());
        vehicledto.setOdometer(vehicle.getOdometer());
        vehicledto.setManufactDate(vehicle.getManufactDate().toString());*/
        return vehicledto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<ModelDto> getModelList(){
        String queryString = "SELECT m FROM Model m";
        TypedQuery<Model> query = this.entityManager.createQuery(queryString,Model.class);
        List<Model> modelList = query.getResultList();
        List<ModelDto> models = new ArrayList<ModelDto>();
        for (Model model:modelList) {
            ModelDto dto = new ModelDto();
            dto.setModelId(model.getVehicleModelId());
            dto.setModelName(model.getVehicleType());
//            dto.setEngineSize(model.getEngineSize());
            dto.setFuelType(model.getFuelType());
            dto.setManufacturer(model.getManufacturer());
            dto.setVehicleType(model.getVehicleType());
            models.add(dto);
        }
        return models;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(VehicleDto vehicle){
        Vehicle vehi = new Vehicle();
        vehi.setVehicleModelId(vehicle.getVehicleModelId());
        vehi.setOdometer(vehicle.getOdometer());
        vehi.setManufactDate(vehicle.getManufactDate());
        vehi.setNumberPlate(vehicle.getNumberPlate());
        vehi.setCustomer(vehicle.getCustomer());
//        this.entityManager.refresh(vehi);
    }
}
