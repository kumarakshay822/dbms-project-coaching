package com.dbms.coaching.services;

import com.dbms.coaching.models.User;

public interface UserService {
    public User save(User user);
    public User findByEmailAddress(String emailAddress);
    public User findByUsername(String username);
    public User setPasswordActivateUserAndEmail(User user);
}
