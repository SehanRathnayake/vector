package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.dto.CustomerDto;
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
import javax.validation.constraints.Null;
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

    public void removeCustomer(long id){
        String queryString = "SELECT c FROM Customer c WHERE c.cus_id = :id";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        query.setParameter("id", id);
        Customer customer = query.getSingleResult();
        System.out.println(customer);
        deleteEntity(customer);
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

    public CustomerDto getSingleCustomer(int id){
        Customer c = getEntity(Customer.class,id);
        CustomerDto customer = new CustomerDto();
        customer.setId(c.getCustomerid());
        customer.setCustName(c.getCustomerName());
        customer.setCustAddress(c.getCustomerAddress());
        customer.setCustTp(c.getCustomerPhone());
        customer.setCustEmail(c.getCustomerEmail());
        return customer;
    }
}
