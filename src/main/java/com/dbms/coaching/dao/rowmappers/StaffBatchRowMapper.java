package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.StaffBatchDetails;
import com.dbms.coaching.models.Batch;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class StaffBatchRowMapper implements RowMapper<StaffBatchDetails> {

    @Override
    public StaffBatchDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);

        StaffBatchDetails staffBatch = (new BeanPropertyRowMapper<>(StaffBatchDetails.class)).mapRow(rs, rowNum);
        staffBatch.setBatch(batch);
        return staffBatch;
    }
}
