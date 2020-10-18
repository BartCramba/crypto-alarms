package com.alarms.cryptoalarms.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserUtil {

    public static String getCurrentUsername() {
        Object prinicipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (prinicipal == null || prinicipal.equals("anonymousUser")) {
            return null;
        }

        return ((User) prinicipal).getUsername();
    }
}
