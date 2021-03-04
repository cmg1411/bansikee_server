package com.tomasfriends.bansikee_server.controller;

import com.tomasfriends.bansikee_server.domain.Question;
import com.tomasfriends.bansikee_server.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }
    // GET 설문지 제공 (질문 조회)

//    @ApiOperation(value ="설문지 제공")
    @GetMapping("/questions")
    public List<Question> getSentence() {

        List<Question> questions = questionService.getQuestions();
        return questions;

    }

    // POST 응답 결과 받기
    // GET 식물 추천
    // POST 찜하기
}
