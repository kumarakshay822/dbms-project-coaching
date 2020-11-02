package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.dao.rowmappers.EnrollmentRowMapper;
import com.dbms.coaching.models.Enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollment (studentId, batchId, courseId, transactionId, joinDate, endDate) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, enrollment.getStudentId(), enrollment.getBatchId(), enrollment.getCourseId(), enrollment.getTransaction().getTransactionId(),
                enrollment.getJoinDate(), enrollment.getEndDate());
    }

    @Override
    public List<Enrollment> getAll() {
        String sql = "SELECT * FROM Enrollment";
        List<Enrollment> enrollments = template.query(sql, new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    @Override
    public List<Enrollment> getAllByStudentId(int studentId) {
        String sql = "SELECT * FROM Enrollment WHERE studentId = ?";
        List<Enrollment> enrollments = template.query(sql, new Object[] { studentId }, new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    @Override
    public List<Enrollment> getAllByCourseId(String courseId) {
        String sql = "SELECT * FROM Enrollment WHERE courseId = ?";
        List<Enrollment> enrollments = template.query(sql, new Object[] { courseId },
                new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    @Override
    public List<Enrollment> getAllByBatch(String courseId, String batchId) {
        String sql = "SELECT * FROM Enrollment WHERE courseId = ? AND batchId = ?";
        List<Enrollment> enrollments = template.query(sql, new Object[] { courseId, batchId },
                new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    @Override
    public Enrollment get(int enrollmentId) {
        try {
            String sql = "SELECT * FROM Enrollment NATURAL JOIN Transaction WHERE enrollmentId = ?";
            Enrollment enrollment = template.queryForObject(sql, new Object[] { enrollmentId },
                    new EnrollmentRowMapper());
            return enrollment;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update joinDate and endDate
     */
    @Override
    public void update(Enrollment enrollment) {
        String sql = "UPDATE Enrollment SET joinDate = ?, endDate = ? WHERE enrollmentId = ?";
        template.update(sql, enrollment.getJoinDate(), enrollment.getEndDate(), enrollment.getEnrollmentId());
    }

    @Override
    public void delete(int enrollmentId) {
        String sql = "DELETE FROM Enrollment WHERE enrollmentId = ?";
        template.update(sql, enrollmentId);
    }

}
