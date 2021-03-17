package com.tomasfriends.bansikee_server.sign.service.exceptions;

public class CommunicationException extends RuntimeException {

    public CommunicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CommunicationException(String msg) {
        super(msg);
    }

    public CommunicationException() {
        super();
    }
}
