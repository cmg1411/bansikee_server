package com.tomasfriends.bansikee_server.exceptions;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidPasswordException(String msg) {
        super(msg);
    }

    public InvalidPasswordException() {
        super();
    }
}
