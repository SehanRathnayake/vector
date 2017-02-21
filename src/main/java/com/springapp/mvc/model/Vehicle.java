package com.springapp.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Heshani Samarasekara on 8/16/2016.
 */

@Entity
@Table(name = "T_VEHICLE")
public class Vehicle implements Serializable{

    @Id
    @Column (name = "VEHICLE_ID")
    private int vehicleId;

    @Column (name = "VEHICLE_MODEL_ID")
    private int vehicleModelId;

    @Column (name = "NUMBER_PLATE")
    private String numberPlate;

    @Column (name = "MANUFACT_DATE")
    private Date manufactDate;

    @Column (name = "ODOMETER")
    private int odometer;

    @Column (name = "CUSTOMER")
    private int customer;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(int vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Date getManufactDate() {
        return manufactDate;
    }

    public void setManufactDate(Date manufactDate) {
        this.manufactDate = manufactDate;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }


}
