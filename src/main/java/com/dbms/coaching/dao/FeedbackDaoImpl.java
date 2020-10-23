package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Feedback;
import com.dbms.coaching.utils.DateTimeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl implements FeedbackDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Override
    public void save(Feedback feedback) {
        String sql = "INSERT INTO Feedback (studentId, employeeId, date, time, subject, message, response) VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, feedback.getStudentId(), feedback.getEmployeeId(),
                dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"), dateTimeUtil.getCurrentDateTime("HH:mm:ss"),
                feedback.getSubject(), feedback.getMessage(), feedback.getResponse());
    }

    @Override
    public Feedback get(int studentId, int employeeId) {
        try {
            String sql = "SELECT * FROM Feedback WHERE studentId = ? AND employeeId = ?";
            Feedback feedback = template.queryForObject(sql, new Object[] { studentId, employeeId },
                    new BeanPropertyRowMapper<>(Feedback.class));
            return feedback;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Feedback> getAll() {
        String sql = "SELECT * FROM Feedback";
        List<Feedback> feedbacks = template.query(sql, new BeanPropertyRowMapper<>(Feedback.class));
        return feedbacks;
    }

    @Override
    public List<Feedback> getAllByStudentId(int studentId) {
        String sql = "SELECT * FROM Feedback WHERE studentId = ?";
        List<Feedback> feedbacks = template.query(sql, new Object[] { studentId },
                new BeanPropertyRowMapper<>(Feedback.class));
        return feedbacks;
    }

    @Override
    public List<Feedback> getAllByEmployeeId(int employeeId) {
        String sql = "SELECT * FROM Feedback WHERE employeeId = ?";
        List<Feedback> feedbacks = template.query(sql, new Object[] { employeeId },
                new BeanPropertyRowMapper<>(Feedback.class));
        return feedbacks;
    }

    @Override
    public void respond(int studentId, int employeeId, String response) {
        String sql = "UPDATE Feedback SET response = ? WHERE studentId = ? AND employeeId = ?";
        template.update(sql, response, studentId, employeeId);
    }

    /**
     * Update all attributes except date, time, studentId, employeeId
     */
    @Override
    public void update(Feedback feedback) {
        String sql = "UPDATE Feedback SET subject = ?, message = ?, response = ? WHERE studentId = ? AND employeeId = ?";
        template.update(sql, feedback.getSubject(), feedback.getMessage(), feedback.getResponse(),
                feedback.getStudentId(), feedback.getEmployeeId());
    }

    @Override
    public void delete(int studentId, int employeeId) {
        String sql = "DELETE FROM Feedback WHERE studentId = ? AND employeeId = ?";
        template.update(sql, studentId, employeeId);
    }

}
