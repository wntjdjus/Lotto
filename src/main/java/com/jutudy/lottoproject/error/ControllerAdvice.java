package com.jutudy.lottoproject.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrorInfo exceptionHandler(NoHandlerFoundException e) {
        return new ErrorInfo(HttpStatus.NOT_FOUND);
    }
}
