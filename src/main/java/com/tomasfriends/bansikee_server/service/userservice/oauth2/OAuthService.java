package com.tomasfriends.bansikee_server.service.userservice.oauth2;

import com.tomasfriends.bansikee_server.dto.controllerdto.oauthprofile.Profile;

public interface OAuthService {
    Profile getProfile(String accessToken);
}
