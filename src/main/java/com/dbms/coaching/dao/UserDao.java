package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.User;

public interface UserDao {
    public User save(User user);

    public List<User> getAll();

    public User get(int userId);

    public boolean exists(String emailAddress);

    public User findByEmailAddress(String emailAddress);

    public User findByUsername(String username);

    public void activate(int userId);

    public void setLoginTimestamp(User user);

    public void setRole(User user, String role);

    public void update(User user);

    public void delete(int userId);
}
