package com.tomasfriends.bansikee_server.onBoarding.repository;

import com.tomasfriends.bansikee_server.onBoarding.domain.OnBoardingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OnBoardingAnswerRepository extends JpaRepository<OnBoardingAnswer, Integer> {

    List findByUserIdx(Integer userIdx);

}
