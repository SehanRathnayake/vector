package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.SubJobDao;
import com.springapp.mvc.model.SubJob;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */

@Repository
public class SubJobDaoImpl extends BaseJpaDaoImpl<SubJob> implements SubJobDao {

    public SubJob createSubJob(SubJob subJob) {

        return saveEntity(subJob);
    }

    public SubJob getSubJob(long jobId, String wheel) {
        String queryString = "SELECT sj FROM SubJob sj" +
                " WHERE sj.jobID = :jobId AND sj.wheel=:wheel";

        TypedQuery<SubJob> query = this.entityManager.createQuery(queryString, SubJob.class);
        query.setParameter("jobId", jobId);
        query.setParameter("wheel", wheel);
        return query.getSingleResult();
    }

    public  HashMap<String, Long> getSubJobs(long jobId) {
        String queryString = "SELECT sj FROM SubJob sj" +
                " WHERE sj.jobID = :jobId";

        TypedQuery<SubJob> query = this.entityManager.createQuery(queryString, SubJob.class);
        query.setParameter("jobId", jobId);
        query.getResultList();

        Iterator<SubJob> iterater = query.getResultList().iterator();
        HashMap<String, Long> subjobs = new HashMap<String, Long>();
        while (iterater.hasNext()) {
            SubJob sj = iterater.next();
            subjobs.put(sj.getWheel(), sj.getSubJobId());
        }
        return subjobs;
    }
}

