package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Guardian;
import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
        student.setUser(user);

        Guardian guardian = (new BeanPropertyRowMapper<>(Guardian.class)).mapRow(rs, rowNum);
        student.setGuardian(guardian);

        return student;
    }
}
