package com.example.blogsearch.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorResponseDto {
    @Builder.Default
    private Integer code = HttpStatus.OK.value();

    @Builder.Default
    private String message = HttpStatus.OK.getReasonPhrase();
}
