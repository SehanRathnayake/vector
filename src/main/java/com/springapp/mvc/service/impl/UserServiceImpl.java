package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.dao.impl.UserDaoImpl;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public String getName(int id) {
        return userDao.getName(id);
    }
}
