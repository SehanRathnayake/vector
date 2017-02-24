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
    private UserDao userDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private JobJdbcDao jobJdbcDao;

//    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public long startNewJob(JobDto jobDto, String username) {
//
//        Job job = new Job();
//        job.setStartedDate(Calendar.getInstance());
//
//        User user = userDao.getUser(username);
//        job.setUser(user);
//
//        job = jobDao.createJob(job);
//
//        List<DeviceMapping> deviceMappings = new ArrayList<DeviceMapping>();
//
//        HashMap deviceSetup = jobDto.getDeviceSetup();
//        Iterator it = deviceSetup.entrySet().iterator();
//
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            DeviceMapping deviceMapping = new DeviceMapping();
//
//            Position position = positionDao.getPositionById((Long) pair.getKey());
//            deviceMapping.setPosition(position);
//
//            Device device = deviceDao.getDeviceById((Long) pair.getValue());
//            deviceMapping.setDevice(device);
//
//            deviceMapping.setJob(job);
//
//            deviceMappings.add(deviceMapping);
//
//            it.remove();
//        }
//
//        job.setDevices(deviceMappings);
//
//        return job.getJobId();
//    }
//
//    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public void createPosition(String description) {
//
//        Position position = new Position();
//        position.setDescription(description);
//        positionDao.createPosition(position);
//
//    }

//    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public void createDevice(String code) {
//
//        Device device = new Device();
//        device.setCode(code);
//        deviceDao.createDevice(device);
//
//    }

//    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public String getPositionDesc(Long id) {
//        return positionDao.getPositionById(id).getDescription();
//    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createNewJob(Long vehicleId) {
        Long jobId = jobJdbcDao.createNewJob(vehicleId);
        return jobId;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long createSubJob(Long jobId, String wheel) {
        Long subJobId = jobJdbcDao.createSubJob(jobId, wheel);
        return subJobId;
    }
}
