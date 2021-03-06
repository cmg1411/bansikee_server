package com.tomasfriends.bansikee_server.controller.logincontroller;

import com.tomasfriends.bansikee_server.domain.response.SuccessCode;
import com.tomasfriends.bansikee_server.domain.response.SuccessResponse;
import com.tomasfriends.bansikee_server.dto.servicedto.BansikeeUser;
import com.tomasfriends.bansikee_server.service.response.ResponseService;
import com.tomasfriends.bansikee_server.service.userservice.BansikeeUserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"3. 회원탈퇴"})
@RestController
@RequiredArgsConstructor
public class DeleteUserController {

    private final BansikeeUserService bansikeeUserService;
    private final ResponseService responseService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원탈퇴", notes = "로그인한 유저 회원탈퇴")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<SuccessResponse> signIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        bansikeeUserService.deleteUser(principal.getId());
        return responseService.getSuccessResult(SuccessCode.DELETE_USER_SUCCESS);
    }
}
