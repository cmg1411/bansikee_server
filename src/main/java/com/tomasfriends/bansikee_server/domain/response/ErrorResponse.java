package com.tomasfriends.bansikee_server.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorResponse {

    private int status;
    private String title;
    private String detail;

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getTitle(), errorCode.getDetail());
    }
}
