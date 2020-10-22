package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Batch;
import com.dbms.coaching.models.Course;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class BatchRowMapper implements RowMapper<Batch> {

    @Override
    public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

        Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
        batch.setCourse(course);

        return batch;
    }
}
