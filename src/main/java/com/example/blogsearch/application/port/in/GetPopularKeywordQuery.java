package com.example.blogsearch.application.port.in;

import com.example.blogsearch.domain.BlogSearchKeyword;

import java.util.List;

public interface GetPopularKeywordQuery {
    List<BlogSearchKeyword> getPopularKeywords(Integer limit);
}
