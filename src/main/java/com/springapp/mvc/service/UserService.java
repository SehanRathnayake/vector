package com.springapp.mvc.service;

import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
public interface UserService {

    public User getUser(String username);

    public List<String> getUserList();

    public void dosomething();

}
