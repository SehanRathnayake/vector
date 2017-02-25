package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.CustomerDao;
import com.springapp.mvc.dto.CustomerDto;
import com.springapp.mvc.model.Customer;
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
    public Customer getCustomer(String customer_name) {
        String queryString = "SELECT c FROM Customer c WHERE c.cus_name = :customer_name";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        query.setParameter("customer_name", customer_name);
        return query.getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> getCustomerList() {
        String queryString = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void removeCustomer(int id){
        int id1 = ((int)id);
        String queryString = "SELECT c FROM Customer c WHERE c.cus_id = :id1";
        TypedQuery<Customer> query = this.entityManager.createQuery(queryString, Customer.class);
        query.setParameter("id1", id1);
        Customer customer = query.getSingleResult();
        System.out.println(customer);
        deleteEntity(customer);
    }

    public Customer createCustomer(CustomerDto cust){
        Customer customer = new Customer();
        customer.setCus_name(cust.getCustName());
        customer.setCus_email(cust.getCustEmail());
        customer.setCus_phone(cust.getCustTp());
        customer.setCus_address(cust.getCustAddress());
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
        customer.setId(c.getCus_id());
        customer.setCustName(c.getCus_name());
        customer.setCustAddress(c.getCus_address());
        customer.setCustTp(c.getCus_phone());
        customer.setCustEmail(c.getCus_email());
        return customer;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(CustomerDto customer){
        Customer cust = new Customer();
        cust.setCus_id(customer.getId());
        cust.setCus_name(customer.getCustName());
        cust.setCus_address(customer.getCustAddress());
        cust.setCus_phone(customer.getCustTp());
        cust.setCus_email(customer.getCustEmail());
        Customer managed = this.entityManager.find(Customer.class,cust.getCus_id());
//        this.entityManager.refresh(cust);
    }
}
