package com.tomasfriends.bansikee_server.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_INVALID_ACCESS_TOKEN(403, "Invalid Token Value"),
    COMMUNICATION_ERROR(420, "Communication Error Occurred");

    private final int status;
    private final String code;
}
