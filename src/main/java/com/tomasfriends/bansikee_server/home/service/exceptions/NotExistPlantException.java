package com.tomasfriends.bansikee_server.home.service.exceptions;

public class NotExistPlantException extends RuntimeException {
    public NotExistPlantException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotExistPlantException(String msg) {
        super(msg);
    }

    public NotExistPlantException() {
        super();
    }
}
