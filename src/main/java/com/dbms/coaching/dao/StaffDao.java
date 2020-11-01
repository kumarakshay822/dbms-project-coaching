package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Staff;

public interface StaffDao {
    public Staff save(Staff staff);

    public List<Staff> getAll();

    public List<Staff> getAllByBatch(String batchId, String courseId);

    public List<Staff> getStaffsInBatch(String batchId, String courseId);

    public List<Staff> getStaffsNotInBatch(String batchId, String courseId);

    public Staff getByEmployeeId(int employeeId);

    public Staff getByUserId(int userId);

    public Integer getEmployeeIdByUserId(int userId);

    public void update(Staff staff);

    public void delete(int staffId);
}
