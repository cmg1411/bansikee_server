package com.tomasfriends.bansikee_server.mypage.advice;

import com.tomasfriends.bansikee_server.mypage.service.exception.NotExistMyPlantException;
import com.tomasfriends.bansikee_server.response.dto.ErrorCode;
import com.tomasfriends.bansikee_server.response.dto.ErrorResponse;
import com.tomasfriends.bansikee_server.sign.service.exceptions.JWTDecodeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class MyPlantAdvice {

    @ExceptionHandler(NotExistMyPlantException.class)
    public ResponseEntity<ErrorResponse> jwtDecodeException(NotExistMyPlantException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_EXIST_PLANT_IN_MYPAGE), HttpStatus.BAD_REQUEST);
    }
}
