package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.Question;

import java.util.List;


public interface QuestionRepository {
    List<Question> findAll();
}
