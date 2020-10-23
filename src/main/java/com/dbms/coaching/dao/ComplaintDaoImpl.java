package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Complaint;
import com.dbms.coaching.utils.DateTimeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ComplaintDaoImpl implements ComplaintDao {
    @Autowired
    private JdbcTemplate template;

    @Autowired
    private DateTimeUtil dateTimeUtil;

    @Override
    public void save(Complaint complaint) {
        String sql = "INSERT INTO Complaint (date, time, subject, description, response, isResolved, studentId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"), dateTimeUtil.getCurrentDateTime("HH:mm:ss"),
                complaint.getSubject(), complaint.getDescription(), complaint.getResponse(), complaint.isIsResolved(),
                complaint.getStudentId());
    }

    @Override
    public Complaint get(int complaintId) {
        try {
            String sql = "SELECT * FROM Complaint WHERE complaintId = ?";
            Complaint complaint = template.queryForObject(sql, new Object[] { complaintId },
                    new BeanPropertyRowMapper<>(Complaint.class));
            return complaint;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Complaint> getAll() {
        String sql = "SELECT * FROM Complaint";
        List<Complaint> complaints = template.query(sql, new BeanPropertyRowMapper<>(Complaint.class));
        return complaints;
    }

    @Override
    public List<Complaint> getAllByStudentId(int studentId) {
        String sql = "SELECT * FROM Complaint WHERE studentId = ?";
        List<Complaint> complaints = template.query(sql, new Object[] { studentId }, new BeanPropertyRowMapper<>(Complaint.class));
        return complaints;
    }

    @Override
    public void resolve(int complaintId, String response) {
        String sql = "UPDATE Complaint SET response = ?, isResolved = ? WHERE complaintId = ?";
        template.update(sql, response, true, complaintId);
    }

    /**
     * Update all attributes except complaintId, studentId, date and time
     */
    @Override
    public void update(Complaint complaint) {
        String sql = "UPDATE Complaint SET subject = ?, description = ?, response = ?, isResolved = ? WHERE complaintId = ?";
        template.update(sql, complaint.getSubject(), complaint.getDescription(), complaint.getResponse(),
                complaint.isIsResolved(), complaint.getComplaintId());
    }

    @Override
    public void delete(int complaintId) {
        String sql = "DELETE FROM Complaint WHERE complaintId = ?";
        template.update(sql, complaintId);
    }

}
