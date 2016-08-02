package com.springapp.mvc.service;

import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
public interface UserService {

    public String getName(int id);

    public List<String> getUserList();
}
