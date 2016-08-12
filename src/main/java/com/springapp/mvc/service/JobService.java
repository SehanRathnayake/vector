package com.springapp.mvc.service;

import com.springapp.mvc.dto.JobDto;
import com.springapp.mvc.model.Position;
import com.springapp.mvc.model.User;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
public interface JobService {

    public long startNewJob(JobDto jobDto, String username);

    public void createPosition(String description);

    public void createDevice(String code);

    public String getPositionDesc(Long id);

}
