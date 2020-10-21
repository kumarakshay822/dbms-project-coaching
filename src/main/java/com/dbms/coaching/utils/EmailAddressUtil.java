package com.dbms.coaching.utils;

import org.springframework.stereotype.Component;

@Component
public class EmailAddressUtil {

    public boolean isValidEmailAddress(String emailAddress) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return emailAddress.matches(regex);
    }
}
