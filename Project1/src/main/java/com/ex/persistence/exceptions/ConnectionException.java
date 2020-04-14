package com.ex.persistence.exceptions;

public class ConnectionException extends RuntimeException
{
    public ConnectionException() {}
    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
