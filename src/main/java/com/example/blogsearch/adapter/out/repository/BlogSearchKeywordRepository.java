package com.example.blogsearch.adapter.out.repository;

import com.example.blogsearch.adapter.out.repository.entity.BlogSearchKeywordEntity;
import com.example.blogsearch.application.port.out.LoadKeywordPort;
import com.example.blogsearch.domain.BlogSearchKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BlogSearchKeywordRepository implements LoadKeywordPort{

    private final BlogSearchKeywordJpaRepository blogSearchKeywordJpaRepository;
    @Override
    public List<BlogSearchKeyword> loadKeywordsSortByPopular(Integer limit) {
        Pageable pageable = PageRequest.of(1, limit, Sort.by("count", "desc"));
        List<BlogSearchKeywordEntity> blogSearchKeywordEntities = blogSearchKeywordJpaRepository.findAll(pageable).toList();
        //TODO: Mapstruct
        List<BlogSearchKeyword> blogSearchKeywords = new ArrayList<>();
        for(BlogSearchKeywordEntity entity: blogSearchKeywordEntities) {
            blogSearchKeywords.add(entity.toDomain());
        }
        return blogSearchKeywords;
    }
}
