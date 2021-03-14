package com.tomasfriends.bansikee_server.sign.controller;

import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.sign.dto.SignInResponseDto;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.BasicLoginUserRequest;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.EmailAndPassword;
import com.tomasfriends.bansikee_server.sign.dto.controllerdto.NickNameRequestDto;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import com.tomasfriends.bansikee_server.sign.service.BansikeeUserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(tags = {"2. 일반 로그인"})
@RestController
@RequiredArgsConstructor
public class BasicLoginController {

    private final BansikeeUserService bansikeeUserService;
    private final ResponseService responseService;

    @ApiOperation(value = "로그인", notes = "이메일 로그인 api")
    @PostMapping("/v1/signin")
    public ResponseEntity<SingleDataSuccessResponse<SignInResponseDto>> signIn(@ApiParam(value = "로그인 정보") @RequestBody @Valid EmailAndPassword emailAndPassword) {
        SignInResponseDto response = bansikeeUserService.signIn(emailAndPassword.getEmail(), emailAndPassword.getPassword());
        return responseService.getSingleResult(response, SuccessCode.SIGN_IN_SUCCESS);
    }

    @ApiOperation(value = "닉네임 중복검사", notes = "닉네임 중복검사 API")
    @PostMapping("/v1/nickNameDuplicate")
    public ResponseEntity<SingleDataSuccessResponse<Boolean>> nickNameDuplicateCheck(@Valid @RequestBody NickNameRequestDto nickName) {
        Boolean isExist = bansikeeUserService.nickNameCheck(nickName.getNickName());
        return responseService.getSingleResult(isExist, SuccessCode.NICKNAME_CHECK_SUCCESS);
    }

    @ApiOperation(value = "회원가입", notes = "일반 로그인 회원가입")
    @PostMapping("/v1/signup")
    public ResponseEntity<SuccessResponse> signUp(@Valid @RequestBody BasicLoginUserRequest user) {
        bansikeeUserService.signUp(user);
        return responseService.getSuccessResult(SuccessCode.SIGN_UP_SUCCESS);
    }

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "자동 로그인", notes = "자동 로그인, 토큰 검사")
    @PostMapping("/autoLogin")
    public ResponseEntity<SuccessResponse> autoLogin() {
        return responseService.getSuccessResult(SuccessCode.LOGIN_SUCCESS);
    }
}
