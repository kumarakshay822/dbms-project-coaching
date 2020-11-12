package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.dao.rowmappers.CourseSubjectRowMapper;
import com.dbms.coaching.models.CourseSubjectDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CourseSubjectDaoImpl implements CourseSubjectDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(String courseId, String subjectId) {
        String sql = "INSERT INTO CourseSubjectDetails (courseId, subjectId) VALUES (?, ?)";
        template.update(sql, courseId, subjectId);
    }

    @Override
    public List<CourseSubjectDetails> getAll() {
        String sql = "SELECT * FROM CourseSubjectDetails NATURAL JOIN Subject";
        return template.query(sql, new CourseSubjectRowMapper());
    }

    @Override
    public void delete(String courseId, String subjectId) {
        String sql = "DELETE FROM CourseSubjectDetails WHERE courseId = ? AND subjectId = ?";
        template.update(sql, courseId, subjectId);
    }

}
