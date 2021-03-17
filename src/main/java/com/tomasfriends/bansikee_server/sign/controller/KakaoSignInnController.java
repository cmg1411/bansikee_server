package com.tomasfriends.bansikee_server.sign.controller;

import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.sign.service.dto.SignInResponseDto;
import com.tomasfriends.bansikee_server.sign.service.dto.TokenRequestDto;
import com.tomasfriends.bansikee_server.sign.service.dto.KakaoProfile;
import com.tomasfriends.bansikee_server.sign.service.dto.Profile;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.sign.service.oauth2.KakaoOAuthService;
import com.tomasfriends.bansikee_server.sign.service.oauth2.OAuthService;
import com.tomasfriends.bansikee_server.sign.service.oauth2.OauthSignUpService;
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
    public ResponseEntity<SingleDataSuccessResponse<SignInResponseDto>> signUpAndSignIn(
        @ApiParam(value = "Access 토큰정보", required = true) @RequestBody @Valid TokenRequestDto tokenDto) {
        Profile kakaoProfile = kakaoService.getProfile(tokenDto);
        SignInResponseDto signInResult = oauthSignUpService.signUpWithKakao((KakaoProfile) kakaoProfile);
        return responseService.getSingleResult(signInResult, SuccessCode.KAKAO_SIGN_IN_SUCCESS);
    }
}
