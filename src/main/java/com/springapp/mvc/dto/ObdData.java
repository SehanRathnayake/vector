package com.springapp.mvc.dto;

import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 16/12/29.
 */
public class ObdData implements Serializable {
    private static final long serialVersionUID = 4044288636523558664L;
    private String speed;
    private String rpm;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }
}
