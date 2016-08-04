package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.dao.UserJdbcDao;
import com.springapp.mvc.dao.impl.UserDaoImpl;
import com.springapp.mvc.dto.UserDetailsForAuthentication;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserJdbcDao userJdbcDao;

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<String> getUserList(){
        return userJdbcDao.getUserList();
    }


}
