package com.springapp.mvc.dto;

import java.io.Serializable;

/**
 * @author MaN
 *         on 2/12/2017.
 */
public class DeviceWheelDto implements Serializable{
    private int deviceId;
    private String wheelName;
    private int status;
    private String partName;
    private boolean fileRecieved;
    private String customerName;
    private String vehicleName;
    private int jobId;
    private String subJob;

    public String getSubJob() {
        return subJob;
    }

    public void setSubJob(String subJob) {
        this.subJob = subJob;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public DeviceWheelDto(int deviceId) {
        this.deviceId = deviceId;
    }

    public DeviceWheelDto() {
    }

    public boolean isFileRecieved() {
        return fileRecieved;
    }

    public void setFileRecieved(boolean fileRecieved) {
        this.fileRecieved = fileRecieved;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getWheelName() {
        return wheelName;
    }

    public void setWheelName(String wheelName) {
        this.wheelName = wheelName;
    }
}
