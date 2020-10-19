package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Teacher;

public interface TeacherDao {
    public void save(Teacher teacher);

    public List<Teacher> getAll();

    public Teacher get(int teacherId);

    public Teacher update(int teacherId);

    public Teacher delete(int teacherId);
}
