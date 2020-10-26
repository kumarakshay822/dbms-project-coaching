package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Employee;
import com.dbms.coaching.models.Attendance;
import com.dbms.coaching.models.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class AttendanceRowMapper implements RowMapper<Attendance> {

    @Override
    public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
        employee.setUser(user);

        Attendance attendance = (new BeanPropertyRowMapper<>(Attendance.class)).mapRow(rs, rowNum);
        attendance.setEmployee(employee);
        return attendance;
    }
}
