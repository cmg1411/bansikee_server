package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.tomasfriends.bansikee_server.domain.response.SuccessResponse;
import com.tomasfriends.bansikee_server.domain.response.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.domain.response.SuccessCode;
import com.tomasfriends.bansikee_server.dto.controllerdto.BasicLoginUserRequest;
import com.tomasfriends.bansikee_server.dto.controllerdto.EmailAndPassword;
import com.tomasfriends.bansikee_server.service.response.ResponseService;
import com.tomasfriends.bansikee_server.service.userservice.BansikeeUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(tags = {"2. 일반 로그인"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class BasicLoginController {

    private final BansikeeUserService bansikeeUserService;
    private final ResponseService responseService;

    @ApiOperation(value = "로그인", notes = "이메일 로그인 api")
    @PostMapping("/signin")
    public ResponseEntity<SingleDataSuccessResponse<String>> signIn(@ApiParam(value = "로그인 정보") @RequestBody @Valid EmailAndPassword emailAndPassword) {
        String jwtToken = bansikeeUserService.signIn(emailAndPassword.getEmail(), emailAndPassword.getPassword());
        return responseService.getSingleResult(jwtToken, SuccessCode.SIGN_IN_SUCCESS);
    }

    @ApiOperation(value = "회원가입", notes = "일반 로그인 회원가입")
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> signUp(@Valid @RequestBody BasicLoginUserRequest user) {
        bansikeeUserService.signUp(user);
        return responseService.getSuccessResult(SuccessCode.SIGN_UP_SUCCESS);
    }
}
