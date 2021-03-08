package com.tomasfriends.bansikee_server.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SuccessCode {
    KAKAO_SIGN_IN_SUCCESS(221, "Kakao Sign In", "카카오 로그인에 성공했습니다."),
    GOOGLE_SIGN_IN_SUCCESS(221, "Google Sign In", "구글 로그인에 성공했습니다."),
    SIGN_IN_SUCCESS(221, "Sign in Success", "로그인에 성공했습니다."),
    SIGN_UP_SUCCESS(220, "Sign up Success", "회원가입에 성공했습니다."),
    DELETE_USER_SUCCESS(225, "Success delete User.", "회원탈퇴에 성공했습니다."),


    //----onBoarding
    GET_ON_BOARDING_SUCCESS(200, "Get onBaording success", "설문지 조회 성공."),
    ON_BOARDING_RESULT_SUCCESS(200, "Post onBaording result success", "설문 결과 보내기 성공."),
    RECOMMEND_PLANT_SUCCESS(200, "Get recommendation success", "추천 식물 조회 성공."),
    LIKE_SUCCESS(200, "Post like success", "좋아요 성공."),
    UNDO_LIKE_SUCCESS(201, "Post undo like success", "좋아요 취소 성공.");

    private final int status;
    private final String title;
    private final String detail;
}
