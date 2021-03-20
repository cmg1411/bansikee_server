package com.tomasfriends.bansikee_server.common;

import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUser {

    private AuthenticationUser() {
    }

    public static BansikeeUser getAuthenticationUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (BansikeeUser) authentication.getPrincipal();
    }
}
