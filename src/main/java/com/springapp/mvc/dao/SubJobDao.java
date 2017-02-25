package com.springapp.mvc.dao;

import com.springapp.mvc.model.SubJob;

import java.util.HashMap;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
public interface SubJobDao {
    public SubJob createSubJob(SubJob subJob);
    public HashMap<String, Long> getSubJobs(long jobId);
}
