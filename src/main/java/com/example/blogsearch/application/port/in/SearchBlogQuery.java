package com.example.blogsearch.application.port.in;

import com.example.blogsearch.domain.BlogSearchResult;

public interface SearchBlogQuery {
    BlogSearchResult searchBlogPosts(String keyword, Integer pageNumber, Integer pageSize, String sortType);
}
