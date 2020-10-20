package com.dbms.coaching.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.dao.rowmappers.TeacherRowMapper;
import com.dbms.coaching.models.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private JdbcTemplate template;

    // @Override
    // public void save(Teacher teacher, User user) {
    //     String sql = "INSERT INTO Teacher (teacherId, gender, dateOfBirth, houseNumber, street, city, state, pincode, schoolAttending, percentage10th, "
    //             + "percentage12th, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //     template.update(sql, teacher.getTeacherId(), teacher.getGender(), teacher.getDateOfBirth(),
    //             teacher.getHouseNumber(), teacher.getStreet(), teacher.getCity(), teacher.getState(),
    //             teacher.getPinCode(), teacher.getSchoolAttending(), teacher.getPercentage10th(),
    //             teacher.getPercentage12th(), user.getUserId());
    // }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT * FROM Teacher NATURAL JOIN User";
        List<Teacher> teachers = template.query(sql, new TeacherRowMapper());
        return teachers;
    }

    @Override
    public Teacher get(int teacherId) {
        try {
            String sql = "SELECT * FROM Teacher NATURAL JOIN User WHERE teacherId = ?";
            return (Teacher) template.queryForObject(sql, new Object[] {
                    teacherId },
                    new TeacherRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Teacher update(int teacherId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Teacher delete(int teacherId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Teacher teacher) {
        // TODO Auto-generated method stub

    }
}
