package com.tomasfriends.bansikee_server.onBoarding.controller;

import com.tomasfriends.bansikee_server.onBoarding.dto.ReqAnswerListDto;
import com.tomasfriends.bansikee_server.onBoarding.dto.ResQuestionDto;
import com.tomasfriends.bansikee_server.onBoarding.dto.ResRecoPlantDto;
import com.tomasfriends.bansikee_server.onBoarding.service.OnBoardingService;

import com.tomasfriends.bansikee_server.response.dto.SingleDataSuccessResponse;
import com.tomasfriends.bansikee_server.response.dto.SuccessCode;
import com.tomasfriends.bansikee_server.response.dto.SuccessResponse;
import com.tomasfriends.bansikee_server.response.service.ResponseService;
import com.tomasfriends.bansikee_server.sign.domain.BansikeeUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"4. 온보딩"})
@RestController
public class OnBoardingController {

    private OnBoardingService onBoardingService;
    private final ResponseService responseService;

    @Autowired
    public OnBoardingController(OnBoardingService onBoardingService, ResponseService responseService) {
        this.onBoardingService = onBoardingService;
        this.responseService = responseService;
    }

    // GET 설문지 조회 (질문 조회)
    // userTable에 온보딩 0일시 1로 바꾸기

    @ApiOperation(value = "온보딩 설문지 조회", notes = " ")
    @GetMapping("/on-boarding")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SingleDataSuccessResponse<List<ResQuestionDto>>> getQuestions() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();

        return responseService.getSingleResult(onBoardingService.getQuestions(userIdx), SuccessCode.GET_ON_BOARDING_SUCCESS);
    }

    // POST 응답 결과 받기
    //모든 문제에 선택지 선택 되었는지 확인 & 익셉션 처리
    @ApiOperation(value = "온보딩 설문 응답", notes = " ")
    @PostMapping("/on-boarding")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SuccessResponse> postAnswer(@RequestBody ReqAnswerListDto reqAnswerDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();
        onBoardingService.postAnswer(userIdx, reqAnswerDto);
        return responseService.getSuccessResult(SuccessCode.ON_BOARDING_RESULT_SUCCESS);
    }


    // GET 식물 추천
    @ApiOperation(value = "식물 추천", notes = " ")
    @GetMapping("/recommendation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SingleDataSuccessResponse<List<ResRecoPlantDto>>> getRecommendPlants() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();

        return responseService.getSingleResult(onBoardingService.getRecommendPlants(userIdx), SuccessCode.RECOMMEND_PLANT_SUCCESS);
    }


    // POST 찜하기
    @ApiOperation(value = "식물 찜하기", notes = "찜하기 / 찜하기 취소.")
    @PostMapping("/plant/{plantidx}/like")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인시 발급 받는 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ResponseEntity<SuccessResponse> postLike(@PathVariable("plantidx") Integer plantIdx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BansikeeUser principal = (BansikeeUser) authentication.getPrincipal();
        Integer userIdx = principal.getId();

        boolean likeResult = onBoardingService.postLike(userIdx, plantIdx);
        if (likeResult) {
            return responseService.getSuccessResult(SuccessCode.LIKE_SUCCESS);
        } else {
            return responseService.getSuccessResult(SuccessCode.UNDO_LIKE_SUCCESS);
        }
    }
}
