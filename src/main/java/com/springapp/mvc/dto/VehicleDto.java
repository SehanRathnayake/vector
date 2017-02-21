package com.springapp.mvc.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Heshani Samarasekara on 2/21/2017.
 */
public class VehicleDto implements Serializable {
    private int vehicleId;
    private int vehicleModelId;
    private String numberPlate;
    private Date manufactDate;
    private int odometer;
    private int customer;

    public int getVehicleId() {
        return vehicleId;
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

    public void setManufactDate(String manufactDate) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = fmt.parse("2001-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.manufactDate = date;
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
