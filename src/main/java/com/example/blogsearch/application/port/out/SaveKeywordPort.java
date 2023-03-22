package com.example.blogsearch.application.port.out;

import com.example.blogsearch.domain.BlogSearchKeyword;

public interface SaveKeywordPort {
    BlogSearchKeyword saveSearchKeyword(BlogSearchKeyword blogSearchKeyword);
}
