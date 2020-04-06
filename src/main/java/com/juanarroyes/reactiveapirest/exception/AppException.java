package com.juanarroyes.reactiveapirest.exception;

import org.springframework.core.NestedRuntimeException;

public class AppException extends NestedRuntimeException {

    public AppException(String msg) {
        super(msg);
    }

    public AppException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
