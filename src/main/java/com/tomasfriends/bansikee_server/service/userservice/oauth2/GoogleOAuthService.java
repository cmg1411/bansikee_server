package com.tomasfriends.bansikee_server.service.userservice.oauth2;

import com.tomasfriends.bansikee_server.dto.controllerdto.oauthprofile.GoogleProfile;
import com.tomasfriends.bansikee_server.dto.controllerdto.oauthprofile.Profile;
import com.tomasfriends.bansikee_server.exceptions.CommunicationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleOAuthService implements OAuthService {

    @Override
    public Profile getProfile(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + accessToken;
        try {
            return restTemplate.getForObject(url, GoogleProfile.class);
        } catch (Exception e) {
            throw new CommunicationException();
        }
    }
}
