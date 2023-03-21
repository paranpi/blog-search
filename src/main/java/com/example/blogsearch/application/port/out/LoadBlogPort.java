package com.example.blogsearch.application.port.out;

import com.example.blogsearch.domain.BlogSearchResult;

public interface LoadBlogPort {
    BlogSearchResult loadByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortType);
}
