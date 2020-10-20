package com.dbms.coaching.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailAddressUtil {

    public boolean isValidEmailAddress(String emailAddress) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return emailAddress.matches(regex);
    }
}
