package com.dbms.coaching.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

    public String generatePassword(int length) {
        String allCaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String allSmall = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String values = allCaps + allSmall + numbers;
        Random random = new Random();
        String password = "";
        for (int i = 0; i < length; i++) {
            password += values.charAt(random.nextInt(values.length()));
        }
        return password;
    }

}
