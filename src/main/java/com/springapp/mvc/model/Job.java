package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
@Entity
@Table(name = "T_JOB")
public class Job implements Serializable {

    @SequenceGenerator(name = "Job_Gen", sequenceName = "Job_Seq")
    @Id
    @GeneratedValue(generator = "Job_Gen")
    @Column(name = "JOB_ID")
    private long jobId;

    @Column(name = "VEHICLE_ID")
    private long vehicleId;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXECUTED_DATE")
    private java.util.Date executedDate;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long subJobId) {
        this.jobId = subJobId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(Date executedDate) {
        this.executedDate = executedDate;
    }
}
