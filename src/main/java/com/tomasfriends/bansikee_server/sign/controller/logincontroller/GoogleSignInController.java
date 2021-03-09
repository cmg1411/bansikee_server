package com.tomasfriends.bansikee_server.sign.controller.logincontroller;

import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.AccessToken;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.oauthprofile.GoogleProfile;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.oauthprofile.Profile;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.sign.service.userservice.oauth2.GoogleOAuthService;
import com.tomasfriends.bansikee_server.sign.service.userservice.oauth2.OAuthService;
import com.tomasfriends.bansikee_server.sign.service.userservice.oauth2.OauthSignUpService;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"1. 소셜 로그인"})
@RestController
public class GoogleSignInController {

    private final OAuthService googleService;
    private final OauthSignUpService oauthSignUpService;
    private final ResponseService responseService;

    @Autowired
    public GoogleSignInController(GoogleOAuthService googleService, OauthSignUpService oauthSignUpService, ResponseService responseService) {
        this.googleService = googleService;
        this.oauthSignUpService = oauthSignUpService;
        this.responseService = responseService;
    }

    @ApiOperation(value = "구글 회원가입", notes = "등록되지 않은 회원은 카카오에 등록된 이메일로 가입. JWT 발급.")
    @PostMapping("/signup/google")
    public ResponseEntity<SingleDataSuccessResponse<String>> signUpAndSignIn(
        @ApiParam(value = "id 토큰") @RequestBody @Valid AccessToken token) {
        Profile googleProfile = googleService.getProfile(token.getAccessToken());
        String jwt = oauthSignUpService.signUpWithGoogle((GoogleProfile) googleProfile);
        return responseService.getSingleResult(jwt, SuccessCode.GOOGLE_SIGN_IN_SUCCESS);
    }
}