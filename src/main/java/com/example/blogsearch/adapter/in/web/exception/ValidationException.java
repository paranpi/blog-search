package com.example.blogsearch.adapter.in.web.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException{
    private final ErrorCode errorCode;

    public ValidationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
