package com.tomasfriends.bansikee_server.sign.service.exceptions;

public class AlreadySignedUpException extends RuntimeException {
    public AlreadySignedUpException(String msg, Throwable t) {
        super(msg, t);
    }

    public AlreadySignedUpException(String msg) {
        super(msg);
    }

    public AlreadySignedUpException() {
        super();
    }
}
