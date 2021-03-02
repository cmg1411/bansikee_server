package com.tomasfriends.bansikee_server.advice;

import com.tomasfriends.bansikee_server.domain.response.ErrorCode;
import com.tomasfriends.bansikee_server.domain.response.ErrorResponse;
import com.tomasfriends.exceptions.CommunicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static com.tomasfriends.bansikee_server.domain.response.ErrorResponse.FiledErrors;

@RestControllerAdvice
public class LoginExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> tokenValidationException(MethodArgumentNotValidException ex) {
        List<FiledErrors> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors()
            .forEach(c -> errors.add(new FiledErrors(((FieldError) c).getField(), c.getDefaultMessage())));

        ErrorResponse response = ErrorResponse.builder()
            .status(ErrorCode.NOT_INVALID_ACCESS_TOKEN.getStatus())
            .code(ErrorCode.NOT_INVALID_ACCESS_TOKEN.getCode())
            .errors(errors)
            .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CommunicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> communicationException(CommunicationException ex) {
        ErrorResponse response = ErrorResponse.builder()
            .status(ErrorCode.COMMUNICATION_ERROR.getStatus())
            .code(ErrorCode.COMMUNICATION_ERROR.getCode())
            .errors(getErrorMessages(ex))
            .build();
        return ResponseEntity.badRequest().body(response);
    }

    private List<FiledErrors> getErrorMessages(Exception e) {
        List<FiledErrors> errors = new ArrayList<>();
        FiledErrors filedErrors = new FiledErrors("error", e.getMessage());
        errors.add(filedErrors);
        return errors;
    }
}
