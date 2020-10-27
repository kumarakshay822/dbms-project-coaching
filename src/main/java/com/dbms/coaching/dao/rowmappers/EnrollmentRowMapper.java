package com.dbms.coaching.dao.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dbms.coaching.models.Transaction;
import com.dbms.coaching.models.Enrollment;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

public class EnrollmentRowMapper implements RowMapper<Enrollment> {

    @Override
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = (new BeanPropertyRowMapper<>(Transaction.class)).mapRow(rs, rowNum);

        Enrollment enrollment = (new BeanPropertyRowMapper<>(Enrollment.class)).mapRow(rs, rowNum);
        enrollment.setTransaction(transaction);
        return enrollment;
    }
}
