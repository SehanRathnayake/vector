package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
@Entity
@Table(name = "T_SUB_JOB")
public class SubJob implements Serializable {
    @SequenceGenerator(name = "Sub_Job_Gen", sequenceName = "Sub_Job_Seq")
    @Id
    @GeneratedValue(generator = "Sub_Job_Gen")
    @Column(name = "SUB_JOB_ID")
    private long subJobId;

    @Column(name = "JOB_ID")
    private long jobID;

    @Column(name = "WHEEL")
    private String wheel;

    public long getSubJobId() {
        return subJobId;
    }

    public void setSubJobId(long subJobId) {
        this.subJobId = subJobId;
    }

    public long getJobID() {
        return jobID;
    }

    public void setJobID(long jobID) {
        this.jobID = jobID;
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }
}
