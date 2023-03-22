package com.example.blogsearch.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogSearchKeyword {
    private Long id;
    private String keyword;
    @Builder.Default
    private Integer count = 0;

    public Integer increaseSearchCount() {
        return ++count;
    }
}
