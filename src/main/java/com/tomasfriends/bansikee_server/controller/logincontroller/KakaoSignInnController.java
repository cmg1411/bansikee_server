package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.tomasfriends.bansikee_server.domain.login.accesstoken.AccessObject;
import com.tomasfriends.bansikee_server.domain.login.profile.KakaoProfile;
import com.tomasfriends.bansikee_server.domain.login.profile.Profile;
import com.tomasfriends.bansikee_server.domain.response.SingleDataResultMessage;
import com.tomasfriends.bansikee_server.service.loginservice.KakaoOAuthService;
import com.tomasfriends.bansikee_server.service.loginservice.OAuthService;
import com.tomasfriends.bansikee_server.service.loginservice.SignUpService;
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

@Api(tags = {"1. 로그인"})
@RestController
public class KakaoSignInnController {

    private final OAuthService kakaoService;
    private final SignUpService signUpService;
    private final ResponseService responseService;

    @Autowired
    public KakaoSignInnController(KakaoOAuthService kakaoService, SignUpService signUpService, ResponseService responseService) {
        this.kakaoService = kakaoService;
        this.signUpService = signUpService;
        this.responseService = responseService;
    }

    @ApiOperation(value = "카카오 회원가입", notes = "등록되지 않은 회원은 카카오에 등록된 이메일로 가입. JWT 발급.")
    @PostMapping("/signup/kakao")
    public ResponseEntity<SingleDataResultMessage<String>> signUpAndSignIn(
        @ApiParam(value = "Access 토큰정보", required = true) @RequestBody @Valid AccessObject accessObject) {
        String accessToken = accessObject.getAccessToken();
        Profile kakaoProfile = kakaoService.getProfile(accessToken);
        return responseService.getSingleResult(signUpService.signUpWithKakao((KakaoProfile) kakaoProfile));
    }
}
