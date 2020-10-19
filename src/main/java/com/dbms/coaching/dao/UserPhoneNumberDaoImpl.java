package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.UserPhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserPhoneNumberDaoImpl implements UserPhoneNumberDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public void save(UserPhoneNumber userPhoneNumber) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserPhoneNumber> getByUserId(int userId) {
        String sql = "SELECT * FROM UserPhoneNumber WHERE userId = ?";
        List<UserPhoneNumber> phoneNumbers = template.query(sql, new BeanPropertyRowMapper<>(UserPhoneNumber.class));
        return phoneNumbers;
    }

    @Override
    public void update(UserPhoneNumber userPhoneNumber) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int studentId) {
        // TODO Auto-generated method stub

    }

}
