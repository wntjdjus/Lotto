package com.jutudy.lottoproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ExceptionInfo exceptionHandler(NoHandlerFoundException e) {
        return new ExceptionInfo(HttpStatus.NOT_FOUND);
    }
}
