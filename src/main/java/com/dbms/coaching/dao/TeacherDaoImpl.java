package com.dbms.coaching.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dbms.coaching.models.Teacher;
import com.dbms.coaching.utils.PreparedStatementUtil;
import com.dbms.coaching.dao.rowmappers.TeacherRowMapper;

@Repository
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    @Override
    public Teacher save(Teacher teacher) {
        String sql = "INSERT INTO Teacher (gender, dateOfBirth, houseNumber, street, city, state, pincode, bachelorsDegree, mastersDegree, "
                + "doctoralDegree, employeeId, subjectId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"teacherId"});
                preparedStatementUtil.setParameters(preparedStatement, teacher.getGender(), teacher.getDateOfBirth(),
                        teacher.getHouseNumber(), teacher.getStreet(), teacher.getCity(), teacher.getState(),
                        teacher.getPinCode(), teacher.getBachelorsDegree(), teacher.getMastersDegree(),
                        teacher.getDoctoralDegree(), teacher.getEmployee().getEmployeeId(), teacher.getSubject().getSubjectId());
                return preparedStatement;
            }
        }, keyHolder);
        int teacherId = keyHolder.getKey().intValue();
        teacher.setTeacherId(teacherId);
        return teacher;
    }

    @Override
    public List<Teacher> getAll() {
        String sql = "SELECT * FROM Teacher NATURAL JOIN Employee NATURAL JOIN User NATURAL JOIN Subject";
        List<Teacher> teachers = template.query(sql, new TeacherRowMapper());
        return teachers;
    }

    @Override
    public List<Teacher> getAllByBatch(String batchId, String courseId) {
        String sql = "SELECT * FROM Teacher NATURAL JOIN TeacherBatchDetails NATURAL JOIN Employee NATURAL JOIN User WHERE batchId = ? AND courseId = ?";
        List<Teacher> teachers = template.query(sql, new Object[] { batchId, courseId }, new TeacherRowMapper());
        return teachers;
    }

    @Override
    public List<Teacher> getTeachersInBatch(String batchId, String courseId) {
        String sql = "SELECT * FROM Teacher NATURAL JOIN Employee NATURAL JOIN User NATURAL JOIN TeacherBatchDetails WHERE batchId = ? AND courseId = ?";
        List<Teacher> subjects = template.query(sql, new Object[] { batchId, courseId }, new TeacherRowMapper());
        return subjects;
    }

    @Override
    public List<Teacher> getTeachersNotInBatch(String batchId, String courseId) {
        String sql = "SELECT * FROM Teacher NATURAL JOIN Employee NATURAL JOIN User WHERE teacherId NOT IN (SELECT teacherId FROM TeacherBatchDetails WHERE batchId = ? AND courseId = ?)";
        List<Teacher> subjects = template.query(sql, new Object[] { batchId, courseId }, new TeacherRowMapper());
        return subjects;
    }

    @Override
    public Teacher getByEmployeeId(int employeeId) {
        try {
            String sql = "SELECT * FROM Teacher NATURAL JOIN Employee NATURAL JOIN User NATURAL JOIN Subject WHERE employeeId = ?";
            return (Teacher) template.queryForObject(sql, new Object[] { employeeId }, new TeacherRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except teacherId and employeeId
     */
    @Override
    public void update(Teacher teacher) {
        String sql = "UPDATE Teacher SET gender = ?, dateOfBirth = ?, houseNumber = ?, street = ?, city = ?, state = ?, pinCode = ?, "
                + "bachelorsDegree = ?, mastersDegree = ?, doctoralDegree = ?, subjectId = ? WHERE teacherId = ?";
        template.update(sql, teacher.getGender(), teacher.getDateOfBirth(), teacher.getHouseNumber(),
                teacher.getStreet(), teacher.getCity(), teacher.getState(), teacher.getPinCode(),
                teacher.getBachelorsDegree(), teacher.getMastersDegree(), teacher.getDoctoralDegree(),
                teacher.getSubject().getSubjectId(), teacher.getTeacherId());
    }

    @Override
    public void delete(int teacherId) {
        String sql = "DELETE FROM Teacher WHERE teacherId = ?";
        template.update(sql, teacherId);
    }

}
