package com.example.blogsearch.adapter.in.web.dto;

import com.example.blogsearch.domain.BlogSearchKeyword;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogSearchKeywordItemResponseDto {
    private String keyword;
    private Integer count;

    public static BlogSearchKeywordItemResponseDto of(BlogSearchKeyword blogSearchKeyword) {
        return BlogSearchKeywordItemResponseDto.builder()
                .keyword(blogSearchKeyword.getKeyword())
                .count(blogSearchKeyword.getCount())
                .build();
    }
}
