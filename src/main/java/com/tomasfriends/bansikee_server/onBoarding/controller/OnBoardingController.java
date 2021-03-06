package com.tomasfriends.bansikee_server.onBoarding.controller;

import com.tomasfriends.bansikee_server.domain.response.ResultMessage;
import com.tomasfriends.bansikee_server.domain.response.SingleDataResultMessage;
import com.tomasfriends.bansikee_server.onBoarding.dto.ReqAnswerDto;
import com.tomasfriends.bansikee_server.onBoarding.dto.ResQuestionDto;
import com.tomasfriends.bansikee_server.onBoarding.dto.ResRecoPlantListDto;
import com.tomasfriends.bansikee_server.onBoarding.service.QuestionService;

import com.tomasfriends.bansikee_server.service.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"2. 온보딩"})
@RestController
public class OnBoardingController {

    private QuestionService questionService;
    private final ResponseService responseService;

    @Autowired
    public OnBoardingController(QuestionService questionService, ResponseService responseService) {
        this.questionService = questionService;
        this.responseService = responseService;
    }

    // GET 설문지 제공 (질문 조회)
    @ApiOperation(value = "온보딩 설문지 조회", notes = " ")
    @GetMapping("/on-boarding")
    public ResponseEntity<SingleDataResultMessage <List<ResQuestionDto>>> getQuestions() {

        return responseService.getSingleResult(questionService.getQuestions());

    }


    // POST 응답 결과 받기
    @ApiOperation(value = "온보딩 설문 응답", notes = " ")
    @PostMapping("/on-boarding")
    public ResponseEntity<ResultMessage> postAnswer(@RequestHeader Integer userIdx,
                                                    @RequestBody ReqAnswerDto reqAnswerDto) {
        questionService.postAnswer(userIdx, reqAnswerDto);
        return responseService.getSuccessResult();
    }



    // GET 식물 추천
    @ApiOperation(value = "식물 추천", notes = " ")
    @GetMapping("/recommendation")
    public ResponseEntity<SingleDataResultMessage<ResRecoPlantListDto>> getRecommendPlants(@RequestHeader Integer userIdx){
        return responseService.getSingleResult(questionService.getRecommendPlants(userIdx));
    }


    // POST 찜하기
    @ApiOperation(value = "식물 찜하기", notes = "찜하기 / 찜하기 취소.")
    @PostMapping("/plant/{plantidx}/like")
    public ResponseEntity<ResultMessage> postLike(@RequestHeader Integer userIdx,
                                     @PathVariable("plantidx") Integer plantIdx){

        questionService.postLike(userIdx, plantIdx);
        return responseService.getSuccessResult();
    }
}
