package com.tomasfriends.bansikee_server.domain.jwt;

import com.tomasfriends.bansikee_server.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            String token = jwtProvider.resolveToken((HttpServletRequest) request);

            if (token == null || !jwtProvider.validateToken(token)) {
                throw new JWTDecodeException();
            }

            Authentication auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
    }
}
