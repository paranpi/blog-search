package com.example.blogsearch.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class BlogSearchRequestDto {
    @NotEmpty
    private String query;

    private String sort;

    @Min(value = 1)
    @Max(value = 50)
    @Builder.Default
    private Integer page = 1;

    @Min(value = 1)
    @Max(value = 50)
    @Builder.Default
    private Integer size = 10;
}
