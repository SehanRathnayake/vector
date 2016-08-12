package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 16/08/12.
 */


@Entity
@Table(name = "T_DEVICE")
public class Device implements Serializable {

    private static final long serialVersionUID = 1960820962066482915L;

    @SequenceGenerator(name = "Device_Gen", sequenceName = "Device_Seq")
    @Id
    @GeneratedValue(generator = "Device_Gen")
    @Column(name = "DEVICE_ID")
    private long deviceId;

    @Column(name = "DEVICE_CODE")
    private String code;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
