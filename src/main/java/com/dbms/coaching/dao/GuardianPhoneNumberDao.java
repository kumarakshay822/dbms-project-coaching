package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.GuardianPhoneNumber;

public interface GuardianPhoneNumberDao {
    public void save(GuardianPhoneNumber guardianPhoneNumber);

    public List<GuardianPhoneNumber> getByStudentId(int studentId);

    public void update(GuardianPhoneNumber guardianPhoneNumber, int oldPhoneNumber);

    public void delete(GuardianPhoneNumber guardianPhoneNumber);
}
