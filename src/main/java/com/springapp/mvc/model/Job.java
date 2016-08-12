package com.springapp.mvc.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
@Entity
@Table(name = "T_JOB")
public class Job implements Serializable {

    private static final long serialVersionUID = 1960820966066482615L;

    @SequenceGenerator(name = "Job_Gen", sequenceName = "Job_Seq")
    @Id
    @GeneratedValue(generator = "Job_Gen")
    @Column(name = "JOB_ID")
    private long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Calendar startedDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "job")
    private List<DeviceMapping> devices;


    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Calendar date) {
        this.startedDate = date;
    }

    public List<DeviceMapping> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceMapping> devices) {
        this.devices = devices;
    }
}
