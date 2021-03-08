package com.tomasfriends.bansikee_server.onBoarding.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class OnBoardingAnswerPK implements Serializable {
    private int userIdx;
    private int questionIdx;
}
