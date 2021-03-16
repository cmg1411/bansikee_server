package com.tomasfriends.bansikee_server.sign.advice;

import com.tomasfriends.bansikee_server.response.dto.ErrorCode;
import com.tomasfriends.bansikee_server.response.dto.ErrorResponse;
import com.tomasfriends.bansikee_server.sign.service.exceptions.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
@RestControllerAdvice
@AllArgsConstructor
public class LoginExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> tokenValidationException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_REQUEST_DATA), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<ErrorResponse> jwtDecodeException(JWTDecodeException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_INVALID_JWT_TOKEN), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotRegisteredEmailException.class)
    protected ResponseEntity<ErrorResponse> emailSignInFailedException(HttpServletRequest request, NotRegisteredEmailException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_REGISTERED_EMAIL), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotSamePasswordException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponse> notSamePasswordException(HttpServletRequest request, NotSamePasswordException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_SAME_PASSWORD_AND_REPEATED), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<ErrorResponse> invalidPasswordException(HttpServletRequest request, InvalidPasswordException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.INVALID_PASSWORD), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommunicationException.class)
    public ResponseEntity<ErrorResponse> communicationException(CommunicationException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.COMMUNICATION_ERROR), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadySignedUpException.class)
    public ResponseEntity<ErrorResponse> communicationException(AlreadySignedUpException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.ALREADY_EXIST_EMAIL), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HaveToSignInWithOauthException.class)
    public ResponseEntity<ErrorResponse> communicationException(HaveToSignInWithOauthException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.HAVE_TO_SIGN_IN_WITH_OAUTH), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotRegisteredUserIdException.class)
    public ResponseEntity<ErrorResponse> communicationException(NotRegisteredUserIdException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_REGISTERED_ID), HttpStatus.BAD_REQUEST);
    }

}

