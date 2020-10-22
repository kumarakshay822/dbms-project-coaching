package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.TeacherBatchDetails;

public interface TeacherBatchDao {
    public void save(String teacherId, String batchId, String courseId);

    public List<TeacherBatchDetails> getAll();

    public void delete(String teacherId, String batchId, String courseId);
}
