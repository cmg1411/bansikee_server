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
    DELETE_USER_SUCCESS(225, "Success delete User.", "회원탈퇴에 성공했습니다.");

    private final int status;
    private final String title;
    private final String detail;
}
