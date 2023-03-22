package com.example.blogsearch.adapter.out.repository;

import com.example.blogsearch.adapter.out.repository.entity.BlogSearchKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface BlogSearchKeywordJpaRepository extends JpaRepository<BlogSearchKeywordEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BlogSearchKeywordEntity> findBlogSearchKeywordEntityByKeyword(String keyword);
}
