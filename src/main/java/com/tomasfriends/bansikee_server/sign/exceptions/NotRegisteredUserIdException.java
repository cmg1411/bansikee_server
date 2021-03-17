package com.tomasfriends.bansikee_server.sign.exceptions;

public class NotRegisteredUserIdException extends RuntimeException {
    public NotRegisteredUserIdException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotRegisteredUserIdException(String msg) {
        super(msg);
    }

    public NotRegisteredUserIdException() {
        super();
    }
}
