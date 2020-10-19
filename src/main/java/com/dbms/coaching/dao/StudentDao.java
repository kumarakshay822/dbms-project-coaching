package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;

public interface StudentDao {
    public void save(Student student, User user);

    public List<Student> getAll();

    public Student get(int studentId);

    public Student update(int studentId);

    public Student delete(int studentId);
}
