package com.tomasfriends.bansikee_server.domain.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtProvider {

    @Value("${properties.jwt.secretKey}")
    private String secretKey;
    private static final long JWT_VALID_PERIOD = 1000 * 60L * 60L * 12; // 12시간

    private JwtProvider() {
    }

    public String getJWT (String nickname, String email, String profile) {
        return Jwts.builder()
            .setHeader(setHeader())
            .setClaims(setPayloads(nickname, email, profile))
            .setExpiration(getExpiredTime(JWT_VALID_PERIOD))
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
    }

    private Map<String, Object> setHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        return header;
    }

    private Map<String, Object> setPayloads(String nickname, String email, String profile) {
        Map<String, Object> payloads = new HashMap<>();

        payloads.put("exp", getExpiredTime(JWT_VALID_PERIOD));
        payloads.put("name", nickname);
        payloads.put("email", email);
        payloads.put("profileImage", profile);

        return payloads;
    }

    private Date getExpiredTime(Long expiredTime) {
        Date now = new Date();
        now.setTime(now.getTime() + expiredTime);
        return now;
    }
}
