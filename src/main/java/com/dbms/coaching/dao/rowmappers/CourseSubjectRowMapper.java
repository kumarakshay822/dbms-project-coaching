package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.CourseSubjectDetails;
import com.dbms.coaching.models.Subject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class CourseSubjectRowMapper implements RowMapper<CourseSubjectDetails> {

    @Override
    public CourseSubjectDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

        CourseSubjectDetails courseSubject = (new BeanPropertyRowMapper<>(CourseSubjectDetails.class)).mapRow(rs, rowNum);
        courseSubject.setSubject(subject);
        return courseSubject;
    }
}
