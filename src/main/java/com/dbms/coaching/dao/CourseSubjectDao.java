package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.CourseSubjectDetails;

public interface CourseSubjectDao {
    public void save(String courseId, String subjectId);

    public List<CourseSubjectDetails> getAll();

    public void delete(String courseId, String subjectId);
}
