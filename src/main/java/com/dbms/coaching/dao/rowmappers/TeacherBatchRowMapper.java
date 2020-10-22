package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.TeacherBatchDetails;
import com.dbms.coaching.models.Batch;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class TeacherBatchRowMapper implements RowMapper<TeacherBatchDetails> {

    @Override
    public TeacherBatchDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);

        TeacherBatchDetails teacherBatch = (new BeanPropertyRowMapper<>(TeacherBatchDetails.class)).mapRow(rs, rowNum);
        teacherBatch.setBatch(batch);
        return teacherBatch;
    }
}
