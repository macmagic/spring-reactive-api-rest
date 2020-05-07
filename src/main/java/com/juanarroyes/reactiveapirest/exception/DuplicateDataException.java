package com.juanarroyes.reactiveapirest.exception;

public class DuplicateDataException extends AppException {

    public DuplicateDataException(String msg) {
        super(msg);
    }

    public DuplicateDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
