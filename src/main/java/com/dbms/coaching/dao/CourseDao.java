package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Course;

public interface CourseDao {
    public void save(Course course);

    public List<Course> getAll();

    public Course get(String courseId);

    public void update(Course course);

    public void delete(String courseId);
}
