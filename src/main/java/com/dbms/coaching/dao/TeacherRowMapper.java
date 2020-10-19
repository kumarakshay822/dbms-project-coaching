package com.dbms.coaching.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Employee;
import com.dbms.coaching.models.Subject;
import com.dbms.coaching.models.Teacher;
import com.dbms.coaching.models.User;

import org.springframework.jdbc.core.RowMapper;

public class TeacherRowMapper implements RowMapper<Teacher> {

    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
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

        Employee employee = new Employee(
            rs.getInt("employeeId"),
            rs.getInt("basicSalary"),
            rs.getDate("joinDate"),
            rs.getDate("endDate"),
            rs.getString("panNumber"),
            rs.getString("accountNumber"),
            rs.getString("bankName"),
            rs.getString("bankBranch"),
            rs.getString("ifscCode"),
            user
        );

        Subject subject = new Subject(
            rs.getInt("subjectId"),
            rs.getString("name"),
            rs.getString("description")
        );

        Teacher teacher = new Teacher(
            rs.getInt("teacherId"),
            rs.getString("gender"),
            rs.getDate("dateOfBirth"),
            rs.getString("houseNumber"),
            rs.getString("street"),
            rs.getString("city"),
            rs.getString("state"),
            rs.getInt("pinCode"),
            rs.getString("bachelorsDegree"),
            rs.getString("mastersDegree"),
            rs.getString("doctoralDegree"),
            employee,
            subject
        );

        return teacher;

    }
}
