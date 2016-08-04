package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.dto.UserDetailsForAuthentication;
import com.springapp.mvc.model.Privilege;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/08/04.
 */

//only for authentication purposes

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUser(username);
        if (user == null) {
            LOG.error("No user found for username '" + username + "'.");
            throw new UsernameNotFoundException("No user found for username '" + username + "'.");
        }
        UserDetailsForAuthentication userDetails = new UserDetailsForAuthentication();
        userDetails.setUsername(user.getUserName());
        userDetails.setPassword(user.getPassword());
        userDetails.setAuthorities(getAuthorities(user.getRoles()));

        if (user.getStatus().equals("ACT")) {
            userDetails.setEnabled(true);
        } else {
            userDetails.setEnabled(false);
        }
        userDetails.setFullname(user.getFirstName() + " " + user.getLastName());

        return userDetails;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private HashSet<String> getPrivileges(Collection<Role> roles) {
        HashSet<String> privileges = new HashSet<String>();
        List<Privilege> collection = new ArrayList<Privilege>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getDescription());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(HashSet<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
