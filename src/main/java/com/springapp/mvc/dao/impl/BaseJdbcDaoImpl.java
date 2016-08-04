package com.springapp.mvc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
public class BaseJdbcDaoImpl extends NamedParameterJdbcDaoSupport {

    @Autowired
    @Qualifier("vectorDataSource")
    private DataSource dataSource;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    public void init() {
        this.setDataSource(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

}
