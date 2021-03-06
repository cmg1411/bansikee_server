package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.tomasfriends.bansikee_server.domain.response.SuccessCode;
import com.tomasfriends.bansikee_server.dto.controllerdto.AccessToken;
import com.tomasfriends.bansikee_server.dto.controllerdto.oauthprofile.KakaoProfile;
import com.tomasfriends.bansikee_server.dto.controllerdto.oauthprofile.Profile;
import com.tomasfriends.bansikee_server.domain.response.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.service.userservice.oauth2.KakaoOAuthService;
import com.tomasfriends.bansikee_server.service.userservice.oauth2.OAuthService;
import com.tomasfriends.bansikee_server.service.userservice.oauth2.OauthSignUpService;
import com.tomasfriends.bansikee_server.service.response.ResponseService;
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
public class KakaoSignInnController {

    private final OAuthService kakaoService;
    private final OauthSignUpService oauthSignUpService;
    private final ResponseService responseService;

    @Autowired
    public KakaoSignInnController(KakaoOAuthService kakaoService, OauthSignUpService oauthSignUpService, ResponseService responseService) {
        this.kakaoService = kakaoService;
        this.oauthSignUpService = oauthSignUpService;
        this.responseService = responseService;
    }

    @ApiOperation(value = "카카오 회원가입", notes = "등록되지 않은 회원은 카카오에 등록된 이메일로 가입. JWT 발급.")
    @PostMapping("/signup/kakao")
    public ResponseEntity<SingleDataSuccessResponse<String>> signUpAndSignIn(
        @ApiParam(value = "Access 토큰정보", required = true) @RequestBody @Valid AccessToken token) {
        Profile kakaoProfile = kakaoService.getProfile(token.getAccessToken());
        String jwt = oauthSignUpService.signUpWithKakao((KakaoProfile) kakaoProfile);
        return responseService.getSingleResult(jwt, SuccessCode.KAKAO_SIGN_IN_SUCCESS);
    }
}
