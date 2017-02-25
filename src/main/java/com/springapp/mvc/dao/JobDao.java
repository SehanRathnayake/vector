package com.springapp.mvc.dao;

import com.springapp.mvc.model.Job;

import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public interface JobDao {

    public Job createJob(Job job);

    public void deleteJob(Long id);

    public List<Job> getJobs(long vehicleId);
}
