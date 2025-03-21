package com.example.intia_assurance_test.config.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public static String getPasswordHash(String password) {
//        String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt(30));
//        if (BCrypt.checkpw(password, pw_hash))
//            return pw_hash;
//        else
//            return "It does not match";
//            System.out.println("It does not match");
        return encoder.encode(password);
    }
}
