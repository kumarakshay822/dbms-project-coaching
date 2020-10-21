package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Teacher;

public interface TeacherDao {
    public Teacher save(Teacher teacher);

    public List<Teacher> getAll();

    public Teacher getByEmployeeId(int employeeId);

    public void update(Teacher teacher);

    public void delete(int teacherId);
}
