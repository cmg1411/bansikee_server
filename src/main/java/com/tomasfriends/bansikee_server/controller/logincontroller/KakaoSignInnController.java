package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.tomasfriends.bansikee_server.dto.AccessToken;
import com.tomasfriends.bansikee_server.dto.oauthprofile.KakaoProfile;
import com.tomasfriends.bansikee_server.dto.oauthprofile.Profile;
import com.tomasfriends.bansikee_server.domain.response.SingleDataResultMessage;
import com.tomasfriends.bansikee_server.service.loginservice.KakaoOAuthService;
import com.tomasfriends.bansikee_server.service.loginservice.OAuthService;
import com.tomasfriends.bansikee_server.service.loginservice.SignUpService;
import com.tomasfriends.bansikee_server.service.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
        @ApiParam(value = "Access 토큰정보", required = true) @RequestBody @Valid AccessToken token) {
        Profile kakaoProfile = kakaoService.getProfile(token.getAccessToken());
        return responseService.getSingleResult(signUpService.signUpWithKakao((KakaoProfile) kakaoProfile));
    }
}
