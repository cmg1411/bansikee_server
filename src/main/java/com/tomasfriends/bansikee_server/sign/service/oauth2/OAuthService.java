package com.tomasfriends.bansikee_server.sign.service.oauth2;

import com.tomasfriends.bansikee_server.sign.dto.controllerdto.oauthprofile.Profile;

public interface OAuthService {
    Profile getProfile(String accessToken);
}
