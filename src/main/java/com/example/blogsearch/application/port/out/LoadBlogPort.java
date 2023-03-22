package com.example.blogsearch.application.port.out;

import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchSortType;

public interface LoadBlogPort {
    BlogSearchResult loadByKeyword(String keyword, Integer pageNumber, Integer pageSize, BlogSearchSortType sortType);
}
