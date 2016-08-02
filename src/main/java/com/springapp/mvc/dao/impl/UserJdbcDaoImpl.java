package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.UserJdbcDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
@Repository
public class UserJdbcDaoImpl extends BaseJdbcDaoImpl implements UserJdbcDao {

    public List<String> getUserList() {
        List<String> returnList = null;

        StringBuilder query = new StringBuilder("");
        query.append("SELECT NAME FROM T_USER");

        RowMapper<String> mapper = new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String technology = resultSet.getString("NAME");

                return technology;
            }
        };

        returnList = getNamedParameterJdbcTemplate().query(query.toString(), mapper);

        return returnList;
    }
}
