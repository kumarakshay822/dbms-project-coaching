package com.dbms.coaching.services;

import com.dbms.coaching.models.User;

public interface SecurityService {
    public String findLoggedInUsername();

    public int findLoggedInUserId();

    public String findLoggedInName();

    public String findLoggedInUserRole();

    public User findLoggedInUser();

    public void autoLogin(String username, String password);

    public void autoLogout();

    public boolean isUserDeletedOrUpdated();
}
