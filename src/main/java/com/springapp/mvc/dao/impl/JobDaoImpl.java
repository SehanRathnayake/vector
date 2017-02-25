package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.JobDao;
import com.springapp.mvc.model.Job;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    public List<Job> getJobs(long vehicleId){

        String queryString = "SELECT j FROM Job j where j.vehicleId=:vehicleId ORDER BY j.executedDate desc ";
        TypedQuery<Job> query = this.entityManager.createQuery(queryString, Job.class);
        query.setParameter("vehicleId", vehicleId);
        return query.getResultList();
    }

}

