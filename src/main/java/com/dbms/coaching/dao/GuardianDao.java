package com.dbms.coaching.dao;

import com.dbms.coaching.models.Guardian;

public interface GuardianDao {
    public void save(Guardian guardian);

    public Guardian getByStudentId(int studentId);

    public void update(Guardian guardian);
}
