package com.dbms.coaching.services;

import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.models.MyUserDetails;
import com.dbms.coaching.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username");
        }
        user = userDao.setLoginTimestamp(user);
        return new MyUserDetails(user);
    }

}
