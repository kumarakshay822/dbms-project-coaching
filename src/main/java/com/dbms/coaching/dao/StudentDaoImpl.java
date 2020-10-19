package com.dbms.coaching.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.models.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO Student (studentId, gender, dateOfBirth, houseNumber, street, city, state, pincode, schoolAttending, percentage10th, "
                + "percentage12th, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, student.getStudentId(), student.getGender(), student.getDateOfBirth(),
                student.getHouseNumber(), student.getStreet(), student.getCity(), student.getState(),
                student.getPinCode(), student.getSchoolAttending(), student.getPercentage10th(),
                student.getPercentage12th(), student.getUser().getUserId());
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
        String sql = "DELETE FROM STudent WHERE studentId = ?";
        template.update(sql, studentId);
    }

}
