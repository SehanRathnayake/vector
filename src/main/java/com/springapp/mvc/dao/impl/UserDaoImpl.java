package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.User;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by Sehan Rathnayake on 16/07/29.
 */

@Transactional(propagation = Propagation.REQUIRED)
@ImportResource("classpath:spring-config.xml")
@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    private static final String SELECT_QUERY = "select p from User p";
    public String getName(int id){

        Query query = entityManager.createQuery(SELECT_QUERY);
        List<User> users = (List<User>) query.getResultList();
        return "";
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
