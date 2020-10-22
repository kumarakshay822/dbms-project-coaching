package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDaoImpl implements SubjectDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Subject subject) {
        String sql = "INSERT INTO Subject (subjectId, name, description) VALUES (?, ?, ?)";
        template.update(sql, subject.getSubjectId(), subject.getName(), subject.getDescription());
    }

    @Override
    public List<Subject> getAll() {
        String sql = "SELECT * FROM Subject";
        List<Subject> subjects = template.query(sql, new BeanPropertyRowMapper<>(Subject.class));
        return subjects;
    }

    @Override
    public Subject get(String subjectId) {
        try {
            String sql = "SELECT * FROM Subject WHERE subjectId = ?";
            Subject subject = template.queryForObject(sql, new Object[] { subjectId },
                    new BeanPropertyRowMapper<>(Subject.class));
            return subject;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Subject> getSubjectsInCourse(String courseId) {
        String sql = "SELECT * FROM Subject NATURAL JOIN CourseSubjectDetails WHERE courseId = ?";
        List<Subject> subjects = template.query(sql, new Object[] { courseId },
                new BeanPropertyRowMapper<>(Subject.class));
        return subjects;
    }

    @Override
    public List<Subject> getSubjectsNotInCourse(String courseId) {
        String sql = "SELECT * FROM Subject WHERE subjectId NOT IN (SELECT subjectId FROM CourseSubjectDetails WHERE courseId = ?)";
        List<Subject> subjects = template.query(sql, new Object[] { courseId },
                new BeanPropertyRowMapper<>(Subject.class));
        return subjects;
    }

    /**
     * Update all attributes except subjectId
     */
    @Override
    public void update(Subject subject) {
        String sql = "UPDATE Subject SET name = ?, description = ? WHERE subjectId = ?";
        template.update(sql, subject.getName(), subject.getDescription(), subject.getSubjectId());
    }

    @Override
    public void delete(String subjectId) {
        String sql = "DELETE FROM Subject WHERE subjectId = ?";
        template.update(sql, subjectId);
    }

}
