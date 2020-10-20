package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Staff;

public interface StaffDao {
    public Staff save(Staff staff);

    public List<Staff> getAll();

    public Staff getByEmployeeId(int employeeId);

    public void update(Staff staff);

    public void delete(int staffId);
}
