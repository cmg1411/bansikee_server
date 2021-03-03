package com.tomasfriends.bansikee_server.domain.login.accesstoken;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Builder
public class AccessObject {

    private final String tokenType = "bearer";

    @NotBlank
    private String accessToken;

    @Positive
    private Integer expiresIn;

    @NotBlank
    private String refreshToken;

    @Positive
    private Integer refreshTokenExpiresIn;

    private String scope;
}
