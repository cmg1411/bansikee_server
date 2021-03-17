package com.tomasfriends.bansikee_server.sign.controller;

import com.tomasfriends.bansikee_server.sign.service.exceptions.JWTDecodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ExceptionController {

    @RequestMapping(value="/exception/entrypoint", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public void entrypointException() {
        throw new JWTDecodeException();
    }
}