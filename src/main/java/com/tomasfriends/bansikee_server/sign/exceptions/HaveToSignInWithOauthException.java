package com.tomasfriends.bansikee_server.sign.exceptions;

public class HaveToSignInWithOauthException extends RuntimeException {

    public HaveToSignInWithOauthException(String msg, Throwable t) {
        super(msg, t);
    }

    public HaveToSignInWithOauthException(String msg) {
        super(msg);
    }

    public HaveToSignInWithOauthException() {
        super();
    }
}
