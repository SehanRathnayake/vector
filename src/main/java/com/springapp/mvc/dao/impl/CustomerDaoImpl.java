package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

@Repository
public class CustomerDaoImpl extends BaseJpaDaoImpl<Customer> implements CustomerDao {
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public Customer getCustomer(int customer_id) {

        String queryString = "SELECT c FROM Customer c WHERE c.cus_id = :customer_id";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        query.setParameter("customer_id", customer_id);
        return query.getSingleResult();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
