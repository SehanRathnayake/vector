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
    public Long createNewJob(Long vehicleId) {

        Job job = new Job();
        Date utilDate = new Date(Calendar.getInstance().getTimeInMillis());
        job.setExecutedDate(utilDate);
        job.setVehicleId(vehicleId);
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
    public List<Job> getJobs(long vehicleId) {
        return jobDao.getJobs(vehicleId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public HashMap<String, Long> getSubJobs(long jobId) {
        return subJobDao.getSubJobs(jobId);
    }
}
