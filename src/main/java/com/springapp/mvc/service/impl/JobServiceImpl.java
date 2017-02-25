package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.*;
import com.springapp.mvc.dto.JobDto;
import com.springapp.mvc.model.*;
import com.springapp.mvc.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.*;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
@Service
public class JobServiceImpl implements JobService {

    private Logger LOG = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private JobDao jobDao;

    @Autowired
    private SubJobDao subJobDao;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createNewJob(Long vehicleId, Long userId) {

        Job job = new Job();
        Date utilDate = new Date(Calendar.getInstance().getTimeInMillis());
        job.setExecutedDate(utilDate);
        job.setVehicleId(vehicleId);
        job.setUserId(userId);
        job = jobDao.createJob(job);
        return job.getJobId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createSubJob(Long jobId, String wheel) {
        SubJob s = new SubJob();
        s.setJobID(jobId);
        s.setWheel(wheel);
        s = subJobDao.createSubJob(s);
        return s.getSubJobId();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<JobDto> getJobs(long vehicleId) {
        List<Job> jobList = jobDao.getJobs(vehicleId);
        List<JobDto> jobDtoList = new LinkedList<JobDto>();

        for (Job j : jobList) {
            JobDto jobDto = new JobDto();
            jobDto.setJobId(j.getJobId());
            jobDto.setExecutedDate(j.getExecutedDate());
            jobDto.setSubJobs(subJobDao.getSubJobs(j.getJobId()));
            jobDtoList.add(jobDto);
        }
        return jobDtoList;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public HashMap<String, Long> getSubJobs(long jobId) {
        return subJobDao.getSubJobs(jobId);
    }
}
