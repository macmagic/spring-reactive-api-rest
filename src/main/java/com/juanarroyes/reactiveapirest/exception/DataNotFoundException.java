package com.juanarroyes.reactiveapirest.exception;

public class DataNotFoundException extends AppException {

    public DataNotFoundException(String msg) {
        super(msg);
    }

    public DataNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
