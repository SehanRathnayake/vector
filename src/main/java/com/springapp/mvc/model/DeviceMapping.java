package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
@Entity
@Table(name = "T_DEVICE_MAPPING")
public class DeviceMapping implements Serializable {

    private static final long serialVersionUID = 1960820966066480915L;

    @SequenceGenerator(name = "Device_Mapping_Gen", sequenceName = "Device_Mapping_Seq")
    @Id
    @GeneratedValue(generator = "Device_Mapping_Gen")
    @Column(name = "DEVICE_MAPPING_ID")
    private long deviceMappingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_ID")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEVICE_ID")
    private Device device;

    public long getDeviceMappingId() {
        return deviceMappingId;
    }

    public void setDeviceMappingId(long deviceMappingId) {
        this.deviceMappingId = deviceMappingId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}

