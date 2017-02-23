package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Heshani Samarasekara on 2/22/2017.
 */

@Entity
@Table(name = "T_MANUFACTURER")
public class Manufacturer implements Serializable{
    @SequenceGenerator(name = "Manufacturer_Gen", sequenceName = "Manufacturer_Seq")

    @GeneratedValue(generator = "Manufacturer_Gen")

    @Id
//    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "MANUFACTURER_ID")
    private int manufacturerId;

    @Column(name = "MANUFACT_NAME")
    private String manufactName;

    @Column(name = "VEHICLE_ID")
    private int vehicleId;

    @Column(name = "MODEL_ID")
    private int modelId;

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufactName() {
        return manufactName;
    }

    public void setManufactName(String manufactName) {
        this.manufactName = manufactName;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
