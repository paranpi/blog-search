package com.example.blogsearch.adapter.in.web.exception;

import com.example.blogsearch.adapter.in.web.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ErrorResponseDto exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ErrorResponseDto.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponseDto constraintViolationExceptionHandler(ConstraintViolationException e) {

        log.error(e.getMessage());
        return ErrorResponseDto.builder()
                .code(ErrorCode.BAD_REQUEST.getCode())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    public ErrorResponseDto validationExceptionHandler(ValidationException e) {

        log.error(e.getMessage());
        return ErrorResponseDto.builder()
                .code(ErrorCode.BAD_REQUEST.getCode())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ServletRequestBindingException.class)
    public ErrorResponseDto requstBindingException(ServletRequestBindingException e) {

        log.error(e.getMessage());
        return ErrorResponseDto.builder()
                .code(ErrorCode.BAD_REQUEST.getCode())
                .message(e.getMessage())
                .build();
    }
}
