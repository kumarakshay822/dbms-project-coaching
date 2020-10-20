package com.dbms.coaching.dao;

import java.util.List;

import com.dbms.coaching.models.UserPhoneNumber;

public interface UserPhoneNumberDao {
    public void save(UserPhoneNumber userPhoneNumber);

    public List<UserPhoneNumber> getByUserId(int userId);

    public void update(UserPhoneNumber userPhoneNumber, int oldPhoneNumber);

    public void delete(int phoneNumber, int userId);
}
