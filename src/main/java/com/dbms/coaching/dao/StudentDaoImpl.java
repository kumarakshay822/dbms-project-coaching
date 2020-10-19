package com.dbms.coaching.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    JdbcTemplate template;

    @Override
    public void save(Student student, User user) {
        String sql = "INSERT INTO Student (studentId, gender, dateOfBirth, houseNumber, street, city, pincode, schoolAttending, percentage10th, "
                + "percentage12th, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, student.getStudentId(), student.getGender(), student.getDateOfBirth(), student.getHouseNumber(), student.getStreet(),
                student.getCity(), student.getPinCode(), student.getSchoolAttending(), student.getPercentage10th(), student.getPercentage12th(), user.getUserId());
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
                    new BeanPropertyRowMapper<>(Student.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Student update(int studentId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Student delete(int studentId) {
        // TODO Auto-generated method stub
        return null;
    }
}
