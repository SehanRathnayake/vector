package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
public interface UserDao {

    public User getUser(String username);

}
