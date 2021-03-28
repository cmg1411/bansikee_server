package com.tomasfriends.bansikee_server.user.controller;

import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;
import com.tomasfriends.bansikee_server.user.dto.ReqUserDto;
import com.tomasfriends.bansikee_server.user.dto.ResPlantLikeListDto;
import com.tomasfriends.bansikee_server.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"8.마이페이지"})
@RestController
public class MyPageController {

    private final UserService userService;
    private final ResponseService responseService;

    @Autowired
    public MyPageController(UserService userService, ResponseService responseService){
        this.userService = userService;
        this.responseService = responseService;
    }
    @ApiOperation(value = "좋아요 리스트 조회", notes = " ")
    @GetMapping("/plants-like")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SingleDataSuccessResponse<List<ResPlantLikeListDto>>> getLikePlantList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();
        return responseService.getSingleResult(userService.getPlantLikeList(userIdx), SuccessCode.GET_PLANTLIKELIST);
    }

    @ApiOperation(value = "회원 정보 수정", notes = " ")
    @PatchMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SuccessResponse> patchUserInfo(@RequestBody ReqUserDto reqUserDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();
        userService.patchUserInfo(userIdx, reqUserDto);
        return responseService.getSuccessResult(SuccessCode.PATCH_USERINFO);
    }
}
