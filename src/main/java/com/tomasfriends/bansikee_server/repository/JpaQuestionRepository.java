package com.tomasfriends.bansikee_server.repository;

import com.tomasfriends.bansikee_server.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaQuestionRepository extends JpaRepository<Question, Integer>, QuestionRepository {

    @Override
    List<Question> findAll();
}
