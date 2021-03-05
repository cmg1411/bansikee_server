package com.tomasfriends.bansikee_server.domain.jwt;

import com.tomasfriends.bansikee_server.dto.servicedto.BansikeeUser;
import com.tomasfriends.bansikee_server.service.userservice.UserDetailService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtProvider {
    private static final long JWT_VALID_PERIOD = 1000 * 60L * 60L * 24 * 30; // 30일

    @Value("${properties.jwt.secretKey}")
    private String secretKey;

    @Autowired
    private UserDetailService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getJWT (BansikeeUser user, List<String> roles) {
        Date now = new Date();

        return Jwts.builder()
            .setHeader(setHeader())
            .setIssuedAt(now)
            .setClaims(setPayloads(user, roles))
            .setExpiration(getExpiredTime())
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
            .compact();
    }

    private Map<String, Object> setHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        return header;
    }

    private Map<String, Object> setPayloads(BansikeeUser user, List<String> roles) {
        Map<String, Object> payloads = new HashMap<>();

        payloads.put("exp", getExpiredTime());
        payloads.put("userIdx", user.getId());
        payloads.put("email", user.getEmail());
        payloads.put("roles", roles);

        return payloads;
    }

    private Date getExpiredTime() {
        Date expireDate = new Date();
        expireDate.setTime(expireDate.getTime() + JwtProvider.JWT_VALID_PERIOD);
        return expireDate;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("OAUTH-TOKEN");
    }

    public boolean validateToken (String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
