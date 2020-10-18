package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Student;
import com.dbms.coaching.models.User;

public interface StudentDao {
    public void save(Student student, User user);

    public List<Student> getAllStudents();

    public Student findByStudentId(int studentId);

    public Student updateByStudentId(int studentId);

    public Student deleteByStudentId(int studentId);
}
