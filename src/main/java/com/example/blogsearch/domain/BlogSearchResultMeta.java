package com.example.blogsearch.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BlogSearchResultMeta {
    Integer totalCount;
    Integer pageCount;
}
