package com.jutudy.lottoproject.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class ExceptionInfo {

    private int errorCode;
    private String errorMessage;

    @Builder
    public ExceptionInfo(HttpStatus status) {
        this.errorCode = status.value();
        this.errorMessage = status.name();
    }
}
