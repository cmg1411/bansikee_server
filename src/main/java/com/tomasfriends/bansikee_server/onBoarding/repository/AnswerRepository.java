package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.OnBoardingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<OnBoardingAnswer, Integer> {

}
