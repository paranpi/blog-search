package com.example.blogsearch.adapter.out.repository.entity;

import com.example.blogsearch.domain.BlogSearchKeyword;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlogSearchKeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private Integer count;

    public BlogSearchKeyword toDomain() {
        return BlogSearchKeyword.builder()
                .keyword(keyword)
                .count(count)
                .build();
    }
}
