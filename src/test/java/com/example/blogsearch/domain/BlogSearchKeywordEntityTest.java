package com.example.blogsearch.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlogSearchKeywordEntityTest {
    @Test
    void increaseViewCountTest() {
        BlogSearchKeyword popularKeyword = BlogSearchKeyword.builder()
                .keyword("Test")
                .count(1)
                .build();
        assertEquals(2, (int) popularKeyword.increaseSearchCount());
    }
}
