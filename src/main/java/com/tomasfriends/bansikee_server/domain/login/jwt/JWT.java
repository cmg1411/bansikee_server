package com.tomasfriends.bansikee_server.domain.login.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT {
    private static final String KEY = "tomasWithTomasFriends";
    private static final long JWT_EXPIRED_TIME = 1000 * 60L * 60L * 12; // 12시간

    private JWT() {
    }

    public static String getJWT (String nickname, String email, String profile) {
        return Jwts.builder()
            .setHeader(setHeader())
            .setClaims(setPayloads(nickname, email, profile))
            .setExpiration(getExpiredTime(JWT_EXPIRED_TIME))
            .signWith(SignatureAlgorithm.HS256, KEY.getBytes())
            .compact();
    }

    private static Map<String, Object> setHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        return header;
    }

    private static Map<String, Object> setPayloads(String nickname, String email, String profile) {
        Map<String, Object> payloads = new HashMap<>();

        payloads.put("exp", getExpiredTime(JWT_EXPIRED_TIME));
        payloads.put("name", nickname);
        payloads.put("email", email);
        payloads.put("profileImage", profile);

        return payloads;
    }

    private static Date getExpiredTime(Long expiredTime) {
        Date now = new Date();
        now.setTime(now.getTime() + expiredTime);
        return now;
    }
}
