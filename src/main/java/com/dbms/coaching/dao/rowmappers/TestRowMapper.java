package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Test;
import com.dbms.coaching.models.Course;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class TestRowMapper implements RowMapper<Test> {

    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

        Test test = (new BeanPropertyRowMapper<>(Test.class)).mapRow(rs, rowNum);
        test.setCourse(course);

        return test;
    }
}
