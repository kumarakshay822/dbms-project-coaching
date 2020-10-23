package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Complaint;

public interface ComplaintDao {
    public void save(Complaint complaint);

    public Complaint get(int complaintId);

    public List<Complaint> getAll();

    public List<Complaint> getAllByStudentId(int studentId);

    public void resolve(int complaintId, String response);

    public void update(Complaint complaint);

    public void delete(int complaintId);
}
