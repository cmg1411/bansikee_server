package com.tomasfriends.bansikee_server.sign.exceptions;

public class JWTDecodeException extends RuntimeException {

    public JWTDecodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public JWTDecodeException(String msg) {
        super(msg);
    }

    public JWTDecodeException() {
        super();
    }
}
