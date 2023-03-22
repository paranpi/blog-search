package com.example.blogsearch.application.port.out;

import com.example.blogsearch.domain.BlogSearchKeyword;

import java.util.List;

public interface LoadKeywordPort {
    List<BlogSearchKeyword> loadKeywordsSortByPopular(Integer limit);
}
