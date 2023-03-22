package com.example.blogsearch.adapter.out.repository;

import com.example.blogsearch.adapter.out.repository.entity.BlogSearchKeywordEntity;
import com.example.blogsearch.application.port.out.LoadKeywordPort;
import com.example.blogsearch.application.port.out.SaveKeywordPort;
import com.example.blogsearch.domain.BlogSearchKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BlogSearchKeywordRepository implements LoadKeywordPort, SaveKeywordPort {

    private final BlogSearchKeywordJpaRepository blogSearchKeywordJpaRepository;
    @Override
    public List<BlogSearchKeyword> loadKeywordsSortByPopular(Integer limit) {
        Pageable pageable = PageRequest.of(1, limit, Sort.by("count", "desc"));
        List<BlogSearchKeywordEntity> blogSearchKeywordEntities = blogSearchKeywordJpaRepository.findAll(pageable).toList();
        //TODO: Mapstruct
        List<BlogSearchKeyword> blogSearchKeywords = new ArrayList<>();
        for(BlogSearchKeywordEntity entity: blogSearchKeywordEntities) {
            blogSearchKeywords.add(entity.toDomainEntity());
        }
        return blogSearchKeywords;
    }

    @Override
    public BlogSearchKeyword loadSearchKeyword(String keyword) {
        Optional<BlogSearchKeywordEntity> optionalBlogSearchKeywordEntity = blogSearchKeywordJpaRepository.findBlogSearchKeywordEntityByKeyword(keyword);
        BlogSearchKeywordEntity blogSearchKeywordEntity = optionalBlogSearchKeywordEntity.orElseGet(() -> BlogSearchKeywordEntity.builder().keyword(keyword).count(0).build());
        return blogSearchKeywordEntity.toDomainEntity();
    }

    @Override
    public BlogSearchKeyword saveSearchKeyword(BlogSearchKeyword blogSearchKeyword) {
        BlogSearchKeywordEntity blogSearchKeywordEntity = blogSearchKeywordJpaRepository.save(BlogSearchKeywordEntity.of(blogSearchKeyword));
        return blogSearchKeywordEntity.toDomainEntity();
    }
}
