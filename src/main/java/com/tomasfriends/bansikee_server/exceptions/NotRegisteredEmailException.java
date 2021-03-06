package com.tomasfriends.bansikee_server.exceptions;

public class NotRegisteredEmailException extends RuntimeException{
    public NotRegisteredEmailException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotRegisteredEmailException(String msg) {
        super(msg);
    }

    public NotRegisteredEmailException() {
        super();
    }
}
