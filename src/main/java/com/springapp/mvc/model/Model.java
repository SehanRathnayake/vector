package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Heshani Samarasekara on 2/22/2017.
 */

@Entity
@Table(name = "T_MODEL")
public class Model implements Serializable{
    @SequenceGenerator(name = "Model_Gen", sequenceName = "Model")
    @GeneratedValue(generator = "Model_Gen")

    @Id
//    @OneToMany(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "VEHICLE_MODEL_ID")
    private int vehicleModelId;

    @Column(name = "VEHICLE_TYPE")
    private String vehicleType;

    @Column(name = "ENGINE_SIZE")
    private int engineSize;

    @Column(name = "FUEL_TYPE")
    private String fuelType;

    @Column(name = "VEHICLE_ID")
    private int vehicleId;

    public int getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
