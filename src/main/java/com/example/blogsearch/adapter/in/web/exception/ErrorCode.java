package com.example.blogsearch.adapter.in.web.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(9999, "unknown error"),
    BAD_REQUEST(1000, "invalid parameter");

    final Integer code;

    final String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
