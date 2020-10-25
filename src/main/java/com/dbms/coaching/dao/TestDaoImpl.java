package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.dao.rowmappers.TestRowMapper;
import com.dbms.coaching.models.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Test test) {
        String sql = "INSERT INTO Test (testName, roomNumber, testDate, startTime, endTime, maximumMarks, difficulty, courseId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, test.getTestName(), test.getRoomNumber(), test.getTestDate(), test.getStartTime(),
                test.getEndTime(), test.getMaximumMarks(), test.getDifficulty(), test.getCourse().getCourseId());
    }

    @Override
    public List<Test> getAll() {
        String sql = "SELECT * FROM Test NATURAL JOIN Course";
        List<Test> tests = template.query(sql, new TestRowMapper());
        return tests;
    }

    @Override
    public List<Test> getAllByCourseId(String courseId) {
        String sql = "SELECT * FROM Test NATURAL JOIN Course WHERE courseId = ?";
        List<Test> tests = template.query(sql, new Object[] {courseId}, new TestRowMapper());
        return tests;
    }

    @Override
    public Test get(int testId) {
        try {
            String sql = "SELECT * FROM Test NATURAL JOIN Course WHERE testId = ?";
            Test test = template.queryForObject(sql, new Object[] { testId }, new TestRowMapper());
            return test;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except testId and courseId
     */
    @Override
    public void update(Test test) {
        String sql = "UPDATE Test SET testName = ?, roomNumber = ?, testDate = ?, startTime = ?, endTime = ?, maximumMarks = ?, "
                + "difficulty = ? WHERE testId = ?";
        template.update(sql, test.getTestName(), test.getRoomNumber(), test.getTestDate(), test.getStartTime(), test.getEndTime(),
                test.getMaximumMarks(), test.getDifficulty(), test.getTestId());
    }

    @Override
    public void delete(int testId) {
        String sql = "DELETE FROM Test WHERE testId = ?";
        template.update(sql, testId);
    }

}
