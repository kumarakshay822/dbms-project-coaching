package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Feedback;

public interface FeedbackDao {
    public void save(Feedback feedback);

    public Feedback get(int studentId, int employeeId);

    public List<Feedback> getAll();

    public List<Feedback> getAllByStudentId(int studentId);

    public List<Feedback> getAllByEmployeeId(int employeeId);

    public void respond(int studentId, int employeeId, String response);

    public void update(Feedback feedback);

    public void delete(int studentId, int employeeId);
}
