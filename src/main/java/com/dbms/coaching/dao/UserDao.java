package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.User;

public interface UserDao {
    public void save(User user);
    public List<User> getAllUsers();
    public boolean userExists(String emailAddress);
    public User findByEmailAddress(String emailAddress);
    public User findByUsername(String username);
    public void activateUser(User user);
    public void setLoginTimestamp(User user);
    public void setRole(User user, String role);
}