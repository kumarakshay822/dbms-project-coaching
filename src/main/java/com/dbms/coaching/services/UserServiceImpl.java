package com.dbms.coaching.services;

import com.dbms.coaching.dao.UserDao;
import com.dbms.coaching.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        return userDao.findByEmailAddress(emailAddress);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}