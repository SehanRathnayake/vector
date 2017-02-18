package com.springapp.mvc.service.impl;

import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.dao.UserJdbcDao;
import com.springapp.mvc.dao.impl.UserDaoImpl;
import com.springapp.mvc.dto.UserDetailsForAuthentication;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Sehan Rathnayake on 16/07/29.
 */
@Service
public class UserServiceImpl implements UserService  {

    private Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Override
    @Async
    public void dosomething(){
        int x=5;
        while(x<500){
LOG.error("sdsdsdsdsds "+x);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x++;
        }
    }
    public String configureIPandPort() {
        String ip="localhost";
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        String ipAddress = inetAddress.getHostAddress().toString();
                        System.out.println("IP address" + ipAddress);
                        ip=ipAddress;
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("Socket exception in GetIP Address of Utilities" + ex.toString());
        }
        return ip;
    }

}
