package com.example.blogsearch.adapter.out.repository;

import com.example.blogsearch.adapter.out.repository.entity.BlogSearchKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSearchKeywordJpaRepository extends JpaRepository<BlogSearchKeywordEntity, Long> {
}
