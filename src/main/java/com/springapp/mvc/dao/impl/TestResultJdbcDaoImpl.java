package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.TestResultJdbcDao;
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
public class TestResultJdbcDaoImpl extends BaseJdbcDaoImpl implements TestResultJdbcDao {

    public void saveTestResults(SuspensionTestResults suspensionTestResults, long subJobId) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objOstream = null;
        try {
            objOstream = new ObjectOutputStream(baos);
            objOstream.writeObject(suspensionTestResults);
            byte[] bArray = baos.toByteArray();


            String sql = "INSERT INTO T_RESULT " +
                    "(RESULT_ID,SUB_JOB_ID,RESULT) VALUES (Result_Seq.nextval,:subJobId, :result)";

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("subJobId", subJobId);
            parameters.put("result", bArray);


            getNamedParameterJdbcTemplate().update(sql, parameters);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public SuspensionTestResults getTestResults(long subJob) {
        Result result = this.getJDBCTemplate().queryForObject(
                "select SUB_JOB_ID,RESULT from T_RESULT where SUB_JOB_ID = ? and ROWNUM <=1",
                new Object[]{subJob},
                new RowMapper<Result>() {

                    public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Result result = new Result();
                        result.setId(rs.getInt("SUB_JOB_ID"));
                        Blob b = rs.getBlob("RESULT");
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

                        return result;
                    }
                });
        return result.getSuspensionTestResults();

    }
}
