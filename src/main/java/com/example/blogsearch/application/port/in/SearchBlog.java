package com.example.blogsearch.application.port.in;

import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchSortType;

public interface SearchBlog {
    BlogSearchResult searchBlogPosts(String keyword, Integer pageNumber, Integer pageSize, BlogSearchSortType sortType);
}
