package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.dao.rowmappers.ResultRowMapper;
import com.dbms.coaching.models.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDaoImpl implements ResultDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public void save(Result result) {
        String sql = "INSERT INTO Result (studentId, testId, marksScored, hasAppliedRecheck, isDoneRecheck, recheckComments) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, result.getStudent().getStudentId(), result.getTestId(), result.getMarksScored(),
                result.isHasAppliedRecheck(), result.isIsDoneRecheck(), result.getRecheckComments());
    }

    @Override
    public List<Result> getAllByTestId(int testId) {
        String sql = "SELECT * FROM Result NATURAL JOIN Student NATURAL JOIN User WHERE testId = ? ORDER BY marksScored DESC";
        List<Result> results = template.query(sql, new Object[] { testId }, new ResultRowMapper());
        return results;
    }

    @Override
    public Result get(int testId, int studentId) {
        try {
            String sql = "SELECT * FROM Result NATURAL JOIN Student NATURAL JOIN User WHERE testId = ? AND studentId = ?";
            Result result = template.queryForObject(sql, new Object[] { testId, studentId }, new ResultRowMapper());
            return result;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void applyForRecheck(int testId, int studentId, String recheckComments) {
        String sql = "UPDATE Result SET hasAppliedRecheck = true, recheckComments = ? WHERE testId = ? AND studentId = ?";
        template.update(sql, recheckComments, testId, studentId);
    }

    public void updateMarks(int testId, int studentId, int marks) {
        String sql = "UPDATE Result SET isDoneRecheck = true, marksScored = ? WHERE testId = ? AND studentId = ?";
        template.update(sql, marks, testId, studentId);
    }

    /**
     * Update all attributes except testId and studentId
     */
    @Override
    public void update(Result result) {
        // TODO: Rename certain fields with boolean value
        String sql = "UPDATE Result SET marksScored = ?, hasAppliedRecheck = ?, isDoneRecheck = ?, recheckComments = ? WHERE testId = ? AND studentId = ?";
        template.update(sql, result.getMarksScored(), result.isHasAppliedRecheck(), result.isIsDoneRecheck(),
                result.getRecheckComments(), result.getTestId(), result.getStudent().getStudentId());
    }

    @Override
    public void delete(int testId, int studentId) {
        String sql = "DELETE FROM Result WHERE testId = ? AND studentId = ?";
        template.update(sql, testId, studentId);
    }

}
