package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.Staff;
import com.dbms.coaching.models.User;

public interface StaffDao {
    public void save(Staff staff, User user);

    public List<Staff> getAll();

    public Staff get(int staffId);

    public Staff update(int staffId);

    public Staff delete(int staffId);
}
