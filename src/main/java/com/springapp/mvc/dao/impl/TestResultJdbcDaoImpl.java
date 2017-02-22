package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.UserJdbcDao;
import com.springapp.mvc.dto.Result;
import com.springapp.mvc.dto.SuspensionTestResults;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sehan Rathnayake on 17/02/22.
 */

@Repository
public class TestResultJdbcDaoImpl extends BaseJdbcDaoImpl {

    public void saveJob(SuspensionTestResults suspensionTestResults) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objOstream = null;
        try {
            objOstream = new ObjectOutputStream(baos);
            objOstream.writeObject(suspensionTestResults);
            byte[] bArray = baos.toByteArray();


            String sql = "INSERT INTO T_RESULT " +
                    "(RESULT_ID,RESULTS) VALUES (:jobId, :result)";

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("jobId", 1);
            parameters.put("result", bArray);


            getNamedParameterJdbcTemplate().update(sql, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public SuspensionTestResults getTestResults(Long resultId) {
        Result result = this.getJDBCTemplate().queryForObject(
                "select ROLE_ID, ROLE_NAME from T_RESULT where ROLE_ID = ?",
                new Object[]{resultId},
                new RowMapper<Result>() {

                    public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Result result = new Result();
                        result.setId(rs.getInt("ROLE_ID"));
                        Blob b = rs.getBlob("ROLE_NAME");
                        byte[] bdata = b.getBytes(1, (int) b.length());
                        ByteArrayInputStream in = new ByteArrayInputStream(bdata);
                        ObjectInputStream is = null;
                        try {
                            is = new ObjectInputStream(in);
                            Object ob = is.readObject();
                            result.setSuspensionTestResults((SuspensionTestResults) ob);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                        // actor.setSuspensionTestResults(rs.getString("surname"));
                        return result;
                    }
                });
        return result.getSuspensionTestResults();
//
//        List<Actor> actors = this.jdbcTemplate.query(
//                "select first_name, last_name from t_actor",
//                new RowMapper<Actor>() {
//                    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        Actor actor = new Actor();
//                        actor.setFirstName(rs.getString("first_name"));
//                        actor.setLastName(rs.getString("last_name"));
//                        return actor;
//                    }
//                });
    }
}
