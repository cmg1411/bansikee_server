package com.tomasfriends.bansikee_server.sign.exceptions;

public class NotSamePasswordException extends RuntimeException {

    public NotSamePasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotSamePasswordException(String msg) {
        super(msg);
    }

    public NotSamePasswordException() {
        super();
    }
}
