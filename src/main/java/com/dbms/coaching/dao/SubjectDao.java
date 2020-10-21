package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Subject;

public interface SubjectDao {
    public void save(Subject subject);

    public List<Subject> getAll();

    public Subject get(String subjectId);

    public void update(Subject subject);

    public void delete(String subjectId);
}
