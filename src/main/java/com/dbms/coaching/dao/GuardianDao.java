package com.dbms.coaching.dao;

import com.dbms.coaching.models.Guardian;

public interface GuardianDao {
    public void save(Guardian guardian);

    public String getNameByStudentId(int studentId);

    public void update(Guardian guardian);
}
