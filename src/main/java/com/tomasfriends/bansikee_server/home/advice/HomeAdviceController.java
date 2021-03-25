package com.tomasfriends.bansikee_server.home.advice;

import com.tomasfriends.bansikee_server.home.service.exception.NotExistPlantException;
import com.tomasfriends.bansikee_server.response.dto.ErrorCode;
import com.tomasfriends.bansikee_server.response.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class HomeAdviceController {

    @ExceptionHandler(NotExistPlantException.class)
    protected ResponseEntity<ErrorResponse> tokenValidationException(NotExistPlantException ex) {
        return new ResponseEntity<>(ErrorResponse.of(ErrorCode.NOT_EXIST_PLANT), HttpStatus.BAD_REQUEST);
    }
}
