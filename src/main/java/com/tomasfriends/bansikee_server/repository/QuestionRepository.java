package com.tomasfriends.bansikee_server.repository;

import com.tomasfriends.bansikee_server.domain.Question;

import java.util.List;


public interface QuestionRepository {
    List<Question> findAll();
}
