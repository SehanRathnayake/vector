package com.springapp.mvc.dao;

/**
 * Created by Sehan Rathnayake on 16/08/02.
 */
public interface BaseJpaDao<T> {

    public T getEntity(Class<T> t, Object primaryKey);

    public T saveEntity(T t);

    public void flush();

    public void refresh(T t);

    public void deleteEntity(Class<T> t, Object primaryKey);

    public void deleteEntity(T t);

    public void persistEntity(T t);

}
