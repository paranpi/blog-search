package com.example.blogsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BlogSearchKeyword {
    //TODO: Validate
    private String keyword;
    @Builder.Default
    private Integer count = 0;

    public Integer increaseViewCount() {
        return ++count;
    }
}
