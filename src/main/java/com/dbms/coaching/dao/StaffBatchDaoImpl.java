package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.dao.rowmappers.StaffBatchRowMapper;
import com.dbms.coaching.models.StaffBatchDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StaffBatchDaoImpl implements StaffBatchDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(String staffId, String batchId, String courseId) {
        String sql = "INSERT INTO StaffBatchDetails (staffId, batchId, courseId) VALUES (?, ?, ?)";
        template.update(sql, staffId, batchId, courseId);
    }

    @Override
    public List<StaffBatchDetails> getAll() {
        String sql = "SELECT * FROM StaffBatchDetails NATURAL JOIN Batch";
        return template.query(sql, new StaffBatchRowMapper());
    }

    @Override
    public void delete(String staffId, String batchId, String courseId) {
        String sql = "DELETE FROM StaffBatchDetails WHERE staffId = ? AND batchId = ? AND courseId = ?";
        template.update(sql, staffId, batchId, courseId);
    }

}
