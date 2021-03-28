package com.tomasfriends.bansikee_server.mypage.service.exception;

public class NotExistMyPlantException extends RuntimeException {

    public NotExistMyPlantException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotExistMyPlantException(String msg) {
        super(msg);
    }

    public NotExistMyPlantException() {
        super();
    }
}
