package com.dbms.coaching.dao;

import com.dbms.coaching.models.UserToken;

public interface UserTokenDao {
    public void save(UserToken userToken);

    public Integer getUserIdByToken(String token);

    public String getTokenByUserId(int userId);

    public void update(UserToken userToken);

    public void delete(String token);
}
