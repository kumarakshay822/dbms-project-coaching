package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Employee;
import com.dbms.coaching.models.Subject;
import com.dbms.coaching.models.Teacher;
import com.dbms.coaching.models.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class TeacherRowMapper implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
        employee.setUser(user);

        Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

        Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
        teacher.setEmployee(employee);
        teacher.setSubject(subject);
        return teacher;

    }
}
