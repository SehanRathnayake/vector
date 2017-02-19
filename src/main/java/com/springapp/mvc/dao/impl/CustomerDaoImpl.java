package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.Customer;
import com.springapp.mvc.model.Job;
import com.springapp.mvc.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Heshani Samarasekara on 8/14/2016.
 */

@Repository
public class CustomerDaoImpl extends BaseJpaDaoImpl<Customer> implements CustomerDao {
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> getCustomer(String customer_name) {
        String queryString = "SELECT c FROM Customer c WHERE c.cus_name = :customer_name";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        query.setParameter("customer_name", customer_name);
        return query.getResultList();
    }

    public List<Customer> getCustomerList() {
        String queryString = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        return query.getResultList();
    }

    public Customer createJob(Customer customer){
        return saveEntity(customer);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
