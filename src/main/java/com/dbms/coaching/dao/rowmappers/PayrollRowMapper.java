package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Employee;
import com.dbms.coaching.models.Payroll;
import com.dbms.coaching.models.User;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class PayrollRowMapper implements RowMapper<Payroll> {

    @Override
    public Payroll mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
        employee.setUser(user);

        Payroll payroll = (new BeanPropertyRowMapper<>(Payroll.class)).mapRow(rs, rowNum);
        payroll.setEmployee(employee);
        return payroll;
    }
}
