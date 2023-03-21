package com.example.blogsearch.adapter.in.web.exception;

import com.example.blogsearch.adapter.in.web.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponseDto exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ErrorResponseDto.builder()
                .code(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponseDto constraintViolationExceptionHandler(BindException e) {
        List<String> errorMessages = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        for(FieldError fieldError: bindingResult.getFieldErrors()) {
            errorMessages.add(fieldError.getField() + " 은(는) " + fieldError.getDefaultMessage());
        }


        log.error(String.join(",", errorMessages));
        return ErrorResponseDto.builder()
                .code(ErrorCode.BAD_REQUEST.getCode())
                .message(String.join(",", errorMessages))
                .build();
    }
}
