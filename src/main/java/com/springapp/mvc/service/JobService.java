package com.springapp.mvc.service;



/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public interface JobService {

    // public long startNewJob(JobDto jobDto, String username);

    //  public void createPosition(String description);

    //  public void createDevice(String code);

    //  public String getPositionDesc(Long id);
    public Long createNewJob(Long vehicleId);

    public Long createSubJob(Long jobId, String wheel);

}
