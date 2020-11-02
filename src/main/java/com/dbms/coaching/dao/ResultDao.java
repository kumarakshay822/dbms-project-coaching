package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Result;

public interface ResultDao {
    public void save(Result result);

    public List<Result> getAllByTestId(int testId);

    public List<Result> getAllRechecksByTestId(int testId);

    public Result get(int testId, int studentId);

    public int isStudentAppearedInTest(int testId, int studentId);

    public void applyForRecheck(int testId, int studentId, String recheckComments);

    public void updateMarksAndMarkDone(int testId, int studentId, int marks);

    public void updateMarks(Result result);

    public void update(Result result);

    public void delete(int testId, int studentId);
}
