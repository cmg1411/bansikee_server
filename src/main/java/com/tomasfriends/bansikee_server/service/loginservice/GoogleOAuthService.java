package com.tomasfriends.bansikee_server.service.loginservice;

import com.tomasfriends.bansikee_server.dto.oauthprofile.GoogleProfile;
import com.tomasfriends.bansikee_server.dto.oauthprofile.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleOAuthService implements OAuthService {

    @Override
    public Profile getProfile(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + accessToken;
        return restTemplate.getForObject(url, GoogleProfile.class);
    }
}
