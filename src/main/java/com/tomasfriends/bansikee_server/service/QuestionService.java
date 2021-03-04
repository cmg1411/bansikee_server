package com.tomasfriends.bansikee_server.service;

import com.tomasfriends.bansikee_server.domain.Question;
import com.tomasfriends.bansikee_server.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class QuestionService {

    private  QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    /**
     * 전체 질문 조회
     */
    public List<Question> getQuestions() {
        return questionRepository.findAll();

    }
}
