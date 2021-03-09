package com.tomasfriends.bansikee_server.sign.controller;

import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.sign.dto.servicedto.BansikeeUser;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import com.tomasfriends.bansikee_server.sign.service.userservice.BansikeeUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {"3. 온보딩 여부 가져오기"})
public class UserController {

    private final BansikeeUserService bansikeeUserService;
    private final ResponseService responseService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/isOnboarded")
    public ResponseEntity<SingleDataSuccessResponse<Boolean>> getIsOnboarded() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Boolean isOnBoarded = bansikeeUserService.getIsOnboardedService(principal.getId());
        return responseService.getSingleResult(isOnBoarded, SuccessCode.GET_IS_ONBOARDED_SUCCESS);
    }
}
