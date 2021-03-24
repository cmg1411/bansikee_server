package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.tomasfriends.bansikee_server.sign.service.dto.GoogleProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.Profile;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import com.tomasfriends.bansikee_server.sign.service.exceptions.CommunicationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleOAuthService implements OAuthService {

    @Override
    public Profile getProfile(TokenRequestDto accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + accessToken.getAccessToken();
        try {
            return restTemplate.getForObject(url, GoogleProfile.class);
        } catch (Exception e) {
            throw new CommunicationException();
        }
    }
}
