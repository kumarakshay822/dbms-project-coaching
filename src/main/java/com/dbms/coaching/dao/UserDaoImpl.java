package com.dbms.coaching.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.dbms.coaching.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    JdbcTemplate template;

    /**
     * Get datetime in specified format
     * e.g: yyyy-MM-dd HH:mm:ss
     * 
     * @param format
     * @return String datetime
     */
    public String getCurrentDatetime(String format) {
        SimpleDateFormat localDatetimeFormat = new SimpleDateFormat(format);
        localDatetimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String datetime = localDatetimeFormat.format(new Date());
        return datetime;
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO User (username, password, firstName, middleName, lastName, emailAddress, dateCreated, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getFirstName(), user.getMiddleName(),
                user.getLastName(), user.getEmailAddress(), getCurrentDatetime("yyyy-MM-dd"), user.getRole());
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        String sql = "SELECT * FROM User WHERE emailAddress = ?";
        return (User) template.queryForObject(sql, new Object[] { emailAddress }, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM User WHERE username = ?";
            return (User) template.queryForObject(sql, new Object[] { username }, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void activateUser(User user) {
        String sql = "UPDATE User SET isActive = 'true' WHERE userId = ?";
        template.update(sql, user.getUserId());
    }

    @Override
    public void setLoginTimestamp(User user) {
        String sql = "UPDATE User SET lastLoginDate = ? AND lastLoginTime = ? WHERE userId = ?";
        template.update(sql, getCurrentDatetime("yyyy-MM-dd"), 
                getCurrentDatetime("HH:mm:ss"), user.getUserId());
    }

    @Override
    public void setRole(User user, String role) {
        String sql = "UPDATE User SET role = ? WHERE userId = ?";
        template.update(sql, role, user.getUserId());
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM User";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public boolean userExists(String emailAddress) {
        String sql = "SELECT COUNT(*) FROM User WHERE emailAddress = ?";
        int count = template.queryForObject(sql, Integer.class);
        return (count > 0);
    }
    
}