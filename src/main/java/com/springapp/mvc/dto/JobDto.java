package com.springapp.mvc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public class JobDto implements Serializable {
    private long jobId;

    private java.util.Date executedDate;

    private HashMap<String, Long> subJobs;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public Date getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(Date executedDate) {
        this.executedDate = executedDate;
    }

    public HashMap<String, Long> getSubJobs() {
        return subJobs;
    }

    public void setSubJobs(HashMap<String, Long> subJobs) {
        this.subJobs = subJobs;
    }
}
