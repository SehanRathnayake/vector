package com.springapp.mvc.dao;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
public interface JobJdbcDao {
    public Long createNewJob(Long vehicleId);
    public Long createSubJob(Long jobId, String Position);
}
