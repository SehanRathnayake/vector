package com.springapp.mvc.dao.impl;

import com.springapp.mvc.dao.BaseJpaDao;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
@ImportResource("classpath:spring-config.xml")
@Repository
public class BaseJpaDaoImpl<T> implements BaseJpaDao<T>{

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T getEntity(Class<T> t, Object primaryKey) {
        return this.entityManager.find(t, primaryKey);
    }

    @Override
    public T saveEntity(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void flush() {
        this.entityManager.flush();
    }

    @Override
    public void refresh(T t) {
        this.entityManager.refresh(t);
    }

    @Override
    public void deleteEntity(Class<T> t, Object primaryKey) {
        T tt = this.getEntity(t, primaryKey);
        this.entityManager.remove(tt);
    }

    @Override
    public void deleteEntity(T t) {
        this.entityManager.remove(t);
    }

    @Override
    public void persistEntity(T t) {
        this.entityManager.persist(t);
    }
}
