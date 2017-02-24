package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.JobJdbcDao;
import com.springapp.mvc.model.Job;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sehan Rathnayake on 17/02/24.
 */
@Repository
public class JobJdbcDaoImpl extends BaseJdbcDaoImpl implements JobJdbcDao {

    public Long createNewJob(Long vehicleId) {

        Date utilDate = new Date(Calendar.getInstance().getTimeInMillis());

        final java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        final  Long vId=vehicleId;
        final String sql = "INSERT INTO T_JOB (VEHICLE_ID,START_DATE) "
                + " VALUES(?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, vId);
                ps.setDate(2, sqlDate);

                return ps;
            }
        }, holder);

        Long jobId = holder.getKey().longValue();
        return jobId;
    }

    public Long createSubJob(final Long jobId, final String wheel) {
        final String sql = "INSERT INTO T_SUB_JOB (JOB_ID,WHEEL) "
                + " VALUES(?,?)";
        KeyHolder holder = new GeneratedKeyHolder();

        getJdbcTemplate().update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, jobId);
                ps.setString(2, wheel);

                return ps;
            }
        }, holder);

        Long subJobId = holder.getKey().longValue();
        return subJobId;
    }


}
