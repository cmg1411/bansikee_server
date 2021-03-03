package com.tomasfriends.bansikee_server.service.loginservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomasfriends.bansikee_server.domain.login.jwt.JWT;
import com.tomasfriends.bansikee_server.domain.login.profile.GoogleProfile;
import com.tomasfriends.bansikee_server.domain.login.profile.Profile;
import com.tomasfriends.bansikee_server.repository.loginrepository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleOAuthService implements OAuthService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public GoogleOAuthService(ObjectMapper objectMapper, UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Profile getProfile(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + accessToken;
        return restTemplate.getForObject(url, GoogleProfile.class);
    }
}
