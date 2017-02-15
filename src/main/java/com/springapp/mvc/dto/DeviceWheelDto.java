package com.springapp.mvc.dto;

import java.io.Serializable;

/**
 * @author MaN
 *         on 2/12/2017.
 */
public class DeviceWheelDto implements Serializable{
    private int deviceId;
    private String wheelName;

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
