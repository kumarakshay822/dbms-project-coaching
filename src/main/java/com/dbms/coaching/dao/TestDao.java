package com.dbms.coaching.dao;

import java.util.List;
import java.util.Map;

import com.dbms.coaching.models.Test;

public interface TestDao {
    public void save(Test test);

    public List<Test> getAll();

    public List<Test> getAllByCourseId(String courseId);

    public List<Map<String, Object>> getAllByStudentId(int studentId);

    public Test get(int testId);

    public Integer getMaximumMarks(int testId);

    public void update(Test test);

    public void delete(int testId);
}
