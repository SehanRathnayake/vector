package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.JobDao;
import com.springapp.mvc.dto.JobDto;
import com.springapp.mvc.model.Job;
import org.springframework.stereotype.Repository;

/**
 * Created by Sehan Rathnayake on 16/08/10.
 */
@Repository
public class JobDaoImpl extends BaseJpaDaoImpl<Job> implements JobDao {


    public Job createJob(Job job) {

        return saveEntity(job);
    }



    public void deleteJob(Long id) {
        deleteEntity(Job.class, id);
    }

}
