package com.dbms.coaching.services;

import com.dbms.coaching.models.User;

public interface UserService {
    public void save(User user);
    public User findByEmailAddress(String emailAddress);
    public User findByUsername(String username);
    // TODO: Add more user services here
}