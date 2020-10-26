package com.dbms.coaching.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.dbms.coaching.dao.rowmappers.AttendanceRowMapper;
import com.dbms.coaching.models.Attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Attendance attendance) {
        String sql = "INSERT INTO Attendance (date, employeeId, isPresent, remarks) VALUES (?, ?, ?, ?)";
        template.update(sql, attendance.getDate(), attendance.getEmployee().getEmployeeId(), attendance.isIsPresent(), attendance.getRemarks());
    }

    @Override
    public Attendance get(Date date, int employeeId) {
        try {
            String sql = "SELECT * FROM Attendance NATURAL JOIN Employee NATURAL JOIN User WHERE date = ? AND employeeId = ?";
            Attendance attendance = template.queryForObject(sql, new Object[] { date, employeeId },
                    new AttendanceRowMapper());
            return attendance;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int getTotalDays() {
        String sql = "SELECT COUNT(DISTINCT date) FROM Attendance";
        int count = template.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<Map<String, Object>> getAllEmployeeWise() {
        String sql = "SELECT employeeId, CONCAT(firstName, ' ', middleName, ' ', lastName) AS name, role, COUNT(date) AS daysPresent "
                + "FROM Attendance NATURAL JOIN Employee NATURAL JOIN User WHERE isPresent = true GROUP BY employeeId, name, role UNION "
                + "SELECT E.employeeId AS employeeId, CONCAT(firstName, ' ', middleName, ' ', lastName) AS name, role, 0 AS daysPresent "
                + "FROM Attendance NATURAL JOIN Employee E NATURAL JOIN User WHERE NOT EXISTS (SELECT A.employeeId FROM Attendance A WHERE isPresent = true AND A.employeeId = E.employeeId)";

        List<Map<String, Object>> attendances = template.queryForList(sql);
        return attendances;
    }

    @Override
    public List<Attendance> getAllByDate(Date date) {
        String sql = "SELECT * FROM Attendance NATURAL JOIN Employee NATURAL JOIN User WHERE date = ?";
        List<Attendance> attendances = template.query(sql, new Object[] { date },
                new AttendanceRowMapper());
        return attendances;
    }

    @Override
    public List<Attendance> getAllByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM Attendance NATURAL JOIN Employee NATURAL JOIN User WHERE employeeId = ?";
        List<Attendance> attendances = template.query(sql, new Object[] { employeeId }, new AttendanceRowMapper());
        return attendances;
    }

    /**
     * Update all attributes except date and employeeId
     */
    @Override
    public void update(Attendance attendance) {
        String sql = "UPDATE Attendance SET isPresent = ?, remarks = ? WHERE date = ? AND employeeId = ?";
        template.update(sql, attendance.isIsPresent(), attendance.getRemarks(), attendance.getDate(), attendance.getEmployee().getEmployeeId());
    }

    @Override
    public void delete(Date date, int employeeId) {
        String sql = "DELETE FROM Attendance WHERE date = ? AND employeeId = ?";
        template.update(sql, date, employeeId);
    }

}
