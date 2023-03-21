package com.example.blogsearch.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BlogSearchResult {
    BlogSearchResultMeta meta;
    List<BlogSearchResultItem> dataList;
}
