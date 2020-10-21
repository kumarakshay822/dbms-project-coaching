package com.dbms.coaching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.dbms.coaching.models.User;
import com.dbms.coaching.utils.DateTimeUtil;
import com.dbms.coaching.utils.PreparedStatementUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public User save(User user) {
        String sql = "INSERT INTO User (username, password, firstName, middleName, lastName, emailAddress, dateCreated, isActive, role) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "studentId" });
                preparedStatementUtil.setParameters(preparedStatement, user.getUsername(), user.getPassword(), user.getFirstName(),
                        user.getMiddleName(), user.getLastName(), user.getEmailAddress(), dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"),
                        user.isIsActive(), user.getRole());
                return preparedStatement;
            }
        }, keyHolder);
        int userId = keyHolder.getKey().intValue();
        user.setUserId(userId);
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM User";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User get(int userId) {
        try {
            String sql = "SELECT * FROM User WHERE userId = ?";
            return (User) template.queryForObject(sql, new Object[] { userId },
                    new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean exists(String emailAddress) {
        String sql = "SELECT COUNT(*) FROM User WHERE emailAddress = ?";
        int count = template.queryForObject(sql, Integer.class);
        return (count > 0);
    }

    @Override
    public User findByEmailAddress(String emailAddress) {
        String sql = "SELECT * FROM User WHERE emailAddress = ?";
        return (User) template.queryForObject(sql, new Object[] { emailAddress },
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findByUsername(String username) {
        try {
            String sql = "SELECT * FROM User WHERE username = ?";
            return (User) template.queryForObject(sql, new Object[] { username },
                    new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void activate(int userId) {
        String sql = "UPDATE User SET isActive = true WHERE userId = ?";
        template.update(sql, userId);
    }

    @Override
    public void setLoginTimestamp(User user) {
        String sql = "UPDATE User SET lastLoginDate = ? AND lastLoginTime = ? WHERE userId = ?";
        template.update(sql, dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"),
                dateTimeUtil.getCurrentDateTime("HH:mm:ss"), user.getUserId());
    }

    @Override
    public void setRole(User user, String role) {
        String sql = "UPDATE User SET role = ? WHERE userId = ?";
        template.update(sql, role, user.getUserId());
    }

    @Override
    /**
     * Update all attributes except userId, password, dateCreated, lastLoginDate,
     * lastLoginTime, role
     */
    public void update(User user) {
        String sql = "UPDATE User SET username = ?, firstName = ?, middleName = ?, lastName = ?, emailAddress = ?, "
                + "isActive = ? WHERE userId = ?";
        template.update(sql, user.getUsername(), user.getFirstName(), user.getMiddleName(), user.getLastName(),
                user.getEmailAddress(), user.isIsActive(), user.getUserId());
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM User WHERE userId = ?";
        template.update(sql, userId);
    }

}
