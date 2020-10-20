package com.dbms.coaching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.models.Student;
import com.dbms.coaching.utils.PreparedStatementUtil;
import com.dbms.coaching.dao.rowmappers.StudentRowMapper;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public Student save(Student student) {
        String sql = "INSERT INTO Student (gender, dateOfBirth, houseNumber, street, city, state, pincode, schoolAttending, percentage10th, "
                + "percentage12th, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"studentId"});
                preparedStatementUtil.setParameters(preparedStatement, student.getGender(), student.getDateOfBirth(),
                        student.getHouseNumber(), student.getStreet(), student.getCity(), student.getState(),
                        student.getPinCode(), student.getSchoolAttending(), student.getPercentage10th(),
                        student.getPercentage12th(), student.getUser().getUserId());
                return preparedStatement;
            }
        }, keyHolder);
        int studentId = keyHolder.getKey().intValue();
        student.setStudentId(studentId);
        return student;
    }

    @Override
    public List<Student> getAll() {
        String sql = "SELECT * FROM Student NATURAL JOIN User";
        List<Student> students = template.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student get(int studentId) {
        try {
            String sql = "SELECT * FROM Student NATURAL JOIN User WHERE studentId = ?";
            return (Student) template.queryForObject(sql, new Object[] {
                    studentId },
                    new StudentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except studentId and userId
     */
    @Override
    public void update(Student student) {
        String sql = "UPDATE Student SET gender = ?, dateOfBirth = ?, houseNumber = ?, street = ?, city = ?, state = ?, pinCode = ?, "
                + "schoolAttending = ?, percentage10th = ?, percentage12th = ? WHERE studentId = ?";
        template.update(sql, student.getGender(), student.getDateOfBirth(), student.getHouseNumber(),
                student.getStreet(), student.getCity(), student.getState(), student.getPinCode(),
                student.getSchoolAttending(), student.getPercentage10th(), student.getPercentage12th(),
                student.getStudentId());
    }

    @Override
    public void delete(int studentId) {
        String sql = "DELETE FROM Student WHERE studentId = ?";
        template.update(sql, studentId);
    }

}
