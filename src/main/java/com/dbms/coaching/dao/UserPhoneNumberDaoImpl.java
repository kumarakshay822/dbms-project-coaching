package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.UserPhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserPhoneNumberDaoImpl implements UserPhoneNumberDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(UserPhoneNumber userPhoneNumber) {
        String sql = "INSERT INTO UserPhoneNumber (phoneNumber, userId) VALUES (?, ?)";
        template.update(sql, userPhoneNumber.getPhoneNumber(), userPhoneNumber.getUserId());
    }

    @Override
    public List<UserPhoneNumber> getByUserId(int userId) {
        String sql = "SELECT * FROM UserPhoneNumber WHERE userId = ?";
        List<UserPhoneNumber> phoneNumbers = template.query(sql, new Object[] { userId },
                new BeanPropertyRowMapper<>(UserPhoneNumber.class));
        return phoneNumbers;
    }

    /**
     * Update only the phoneNumber attribute
     */
    @Override
    public void update(UserPhoneNumber userPhoneNumber, int oldPhoneNumber) {
        String sql = "UPDATE UserPhoneNumber SET phoneNumber = ? WHERE phoneNumber = ? AND userId = ?";
        template.update(sql, userPhoneNumber.getPhoneNumber(), oldPhoneNumber, userPhoneNumber.getUserId());
    }

    @Override
    public void delete(UserPhoneNumber userPhoneNumber) {
        String sql = "DELETE FROM UserPhoneNumber WHERE phoneNumber = ? AND userId = ?";
        template.update(sql, userPhoneNumber.getPhoneNumber(), userPhoneNumber.getUserId());
    }

}
