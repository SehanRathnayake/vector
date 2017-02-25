package com.springapp.mvc.service;


import com.springapp.mvc.model.Job;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public interface JobService {

    public Long createNewJob(Long vehicleId,Long userId);

    public Long createSubJob(Long jobId, String wheel);

    public List<Job> getJobs(long vehicleId);

    public HashMap<String, Long> getSubJobs(long jobId);

}
