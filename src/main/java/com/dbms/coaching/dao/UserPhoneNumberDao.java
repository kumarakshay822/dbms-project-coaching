package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.UserPhoneNumber;

public interface UserPhoneNumberDao {
    public List<UserPhoneNumber> getPhoneNumberByUserId(int userId);
}
