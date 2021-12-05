package com.jutudy.lottoproject.error;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
public class ErrorInfo {

    private int errorCode;
    private String errorMessage;

    @Builder
    public ErrorInfo(HttpStatus status) {
        this.errorCode = status.value();
        this.errorMessage = status.name();
    }
}
