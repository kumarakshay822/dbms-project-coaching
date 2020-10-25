package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Test;

public interface TestDao {
    public void save(Test test);

    public List<Test> getAll();

    public List<Test> getAllByCourseId(String courseId);

    public Test get(int testId);

    public void update(Test test);

    public void delete(int testId);
}
