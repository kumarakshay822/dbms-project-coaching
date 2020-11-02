package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Enrollment;

public interface EnrollmentDao {
    public void save(Enrollment enrollment);

    public List<Enrollment> getAll();

    public List<Enrollment> getAllByStudentId(int studentId);

    public List<Enrollment> getAllByCourseId(String courseId);

    public List<Enrollment> getAllByBatch(String courseId, String batchId);

    public Enrollment get(int enrollmentId);

    public void update(Enrollment enrollment);

    public void delete(int enrollmentId);
}
