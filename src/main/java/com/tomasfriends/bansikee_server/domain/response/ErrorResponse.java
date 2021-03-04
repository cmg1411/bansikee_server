package com.tomasfriends.bansikee_server.domain.response;

import lombok.*;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private int status;
    private String code;
    private List<FiledErrors> errors;
    @Builder
    public static class FiledErrors {
        private final String field;
        private final String reason;

        public FiledErrors(String field, String reason) {
            this.field = field;
            this.reason = reason;
        }
    }
}
