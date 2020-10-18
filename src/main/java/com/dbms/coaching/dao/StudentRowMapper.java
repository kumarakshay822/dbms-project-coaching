package com.dbms.coaching.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(
            rs.getInt("userId"),
            rs.getString("username"),
            rs.getString("password"),
            rs.getString("firstName"),
            rs.getString("middleName"),
            rs.getString("lastName"),
            rs.getString("emailAddress"),
            rs.getDate("dateCreated"),
            rs.getBoolean("isActive"),
            rs.getDate("lastLoginDate"),
            rs.getTime("lastLoginTime"),
            rs.getString("role")
        );

        Student student = new Student(
            rs.getInt("studentId"),
            rs.getString("gender"),
            rs.getDate("dateOfBirth"),
            rs.getInt("houseNumber"),
            rs.getString("street"),
            rs.getString("city"),
            rs.getInt("pinCode"),
            rs.getString("schoolAttending"),
            rs.getDouble("percentage10th"),
            rs.getDouble("percentage12th"),
            user
        );

        return student;

    }
}
