package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.dao.rowmappers.BatchRowMapper;
import com.dbms.coaching.models.Batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BatchDaoImpl implements BatchDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Batch batch) {
        String sql = "INSERT INTO Batch (batchId, courseId, batchName, roomNumber, startTime, endTime) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, batch.getBatchId(), batch.getCourse().getCourseId(), batch.getBatchName(),
                batch.getRoomNumber(), batch.getStartTime(), batch.getEndTime());
    }

    @Override
    public List<Batch> getAll() {
        String sql = "SELECT * FROM Batch NATURAL JOIN Course";
        List<Batch> batches = template.query(sql, new BatchRowMapper());
        return batches;
    }

    @Override
    public Batch get(String batchId, String courseId) {
        try {
            String sql = "SELECT * FROM Batch NATURAL JOIN Course WHERE batchId = ? AND courseId = ?";
            Batch batch = template.queryForObject(sql, new Object[] { batchId, courseId }, new BatchRowMapper());
            return batch;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Update all attributes except batchId and courseId
     */
    @Override
    public void update(Batch batch) {
        String sql = "UPDATE Batch SET batchName = ?, roomNumber = ?, startTime = ?, endTime = ? WHERE batchId = ? AND courseId = ?";
        template.update(sql, batch.getBatchName(), batch.getRoomNumber(), batch.getStartTime(), batch.getEndTime(),
                batch.getBatchId(), batch.getCourse().getCourseId());
    }

    @Override
    public void delete(String batchId, String courseId) {
        String sql = "DELETE FROM Batch WHERE batchId = ? AND courseId = ?";
        template.update(sql, batchId, courseId);
    }

}
