package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class OnBoardingAnswer {
    @Id
    private Integer userIdx;

    private Integer questionIdx;

    private Integer optionIdx;

    public static OnBoardingAnswer from(Integer userIdx, Integer questionIdx, Integer optionIdx) {
        OnBoardingAnswer onBoardingAnswer = new OnBoardingAnswer();
        onBoardingAnswer.userIdx = userIdx;
        onBoardingAnswer.questionIdx = questionIdx;
        onBoardingAnswer.optionIdx = optionIdx;
        return onBoardingAnswer;
    }
}
