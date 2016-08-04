package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * Created by Sehan Rathnayake on 16/07/29.
 */


@Repository
public class UserDaoImpl extends BaseJpaDaoImpl<User> implements UserDao {

    @Transactional(propagation = Propagation.REQUIRED)
    public User getUser(String username) {
        String queryString = "SELECT u FROM User u WHERE u.userName = :username";

        TypedQuery<User> query = this.entityManager.createQuery(queryString, User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
