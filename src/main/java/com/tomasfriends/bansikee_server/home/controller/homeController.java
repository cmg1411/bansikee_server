package com.tomasfriends.bansikee_server.home.controller;

import com.tomasfriends.bansikee_server.home.service.HomeService;
import com.tomasfriends.bansikee_server.home.service.dto.HomeResponseDto;
import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"10. 홈 화면"})
@RestController
@RequiredArgsConstructor
public class homeController {

    private final HomeService homeService;
    private final ResponseService responseService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "홈 화면", notes = "자동 로그인, 토큰 검사")
    @PostMapping("/home")
    public ResponseEntity<SingleDataSuccessResponse<HomeResponseDto>> getHomePage() {
        HomeResponseDto homeResult = homeService.getHome();
        return responseService.getSingleResult(homeResult, SuccessCode.HOME_PAGE_RESULT);
    }
}
