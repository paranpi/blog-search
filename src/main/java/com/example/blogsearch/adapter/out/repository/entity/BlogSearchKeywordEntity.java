package com.example.blogsearch.adapter.out.repository.entity;

import com.example.blogsearch.domain.BlogSearchKeyword;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
@Table(indexes = {
        @Index(name = "UK_KEYWORD", columnList = "keyword", unique = true)})
public class BlogSearchKeywordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private Integer count;

    @Builder
    public BlogSearchKeywordEntity(Long id, String keyword, Integer count) {
        this.id = id;
        this.keyword = keyword;
        this.count = count;
    }

    public BlogSearchKeyword toDomainEntity() {
        return BlogSearchKeyword.builder()
                .id(id)
                .keyword(keyword)
                .count(count)
                .build();
    }

    public static BlogSearchKeywordEntity of(BlogSearchKeyword blogSearchKeyword) {
        return BlogSearchKeywordEntity
                .builder()
                .id(blogSearchKeyword.getId())
                .keyword(blogSearchKeyword.getKeyword())
                .count(blogSearchKeyword.getCount())
                .build();
    }
}
