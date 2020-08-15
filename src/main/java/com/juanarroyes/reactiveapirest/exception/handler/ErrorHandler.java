package com.juanarroyes.reactiveapirest.exception.handler;

import com.juanarroyes.reactiveapirest.exception.DataNotFoundException;
import com.juanarroyes.reactiveapirest.exception.DuplicateDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorHandler {

    @ExceptionHandler
    public ResponseEntity handleNotFound(DataNotFoundException e) {
        log.info("Not found exception: {}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleDuplicateData(DuplicateDataException e) {
        log.info("Duplicate data: {}", e.getMessage());
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

}
