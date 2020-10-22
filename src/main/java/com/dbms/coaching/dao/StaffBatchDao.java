package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.StaffBatchDetails;

public interface StaffBatchDao {
    public void save(String staffId, String batchId, String courseId);

    public List<StaffBatchDetails> getAll();

    public void delete(String staffId, String batchId, String courseId);
}
