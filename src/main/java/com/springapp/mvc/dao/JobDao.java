package com.springapp.mvc.dao;

import com.springapp.mvc.dto.JobDto;
import com.springapp.mvc.model.Job;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public interface JobDao {

    public Job createJob(Job job);

    public void deleteJob(Long id);
}
