package com.tomasfriends.bansikee_server.mypage.domain.exception;

public class IllegalAuthUserException extends RuntimeException {

    public IllegalAuthUserException(String msg, Throwable t) {
        super(msg, t);
    }

    public IllegalAuthUserException(String msg) {
        super(msg);
    }

    public IllegalAuthUserException() {
        super();
    }
}
