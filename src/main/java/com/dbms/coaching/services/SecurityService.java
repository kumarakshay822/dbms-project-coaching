package com.dbms.coaching.services;

public interface SecurityService {
    public String findLoggedInUsername();
    public int findLoggedInUserId();
    public String findLoggedInName();
    public String findLoggedInUserRole();
    public void autoLogin(String username, String password);
    public void autoLogout();
}
