package com.example.blogsearch.adapter.in.web;

import com.example.blogsearch.adapter.in.web.dto.BlogSearchKeywordItemResponseDto;
import com.example.blogsearch.adapter.in.web.dto.BlogSearchRequestDto;
import com.example.blogsearch.adapter.in.web.dto.BlogSearchResponseDto;
import com.example.blogsearch.application.service.BlogSearchService;
import com.example.blogsearch.domain.BlogSearchKeyword;
import com.example.blogsearch.domain.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/v1/blog", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@RestController
public class SearchController {
    private final BlogSearchService blogSearchService;
    @GetMapping("/search")
    public BlogSearchResponseDto searchBlogs(@Valid BlogSearchRequestDto requestDto) {
        BlogSearchResult searchResult = blogSearchService.searchBlogPosts(requestDto.getQuery(), requestDto.getPage(), requestDto.getSize(), requestDto.getSort());
        return BlogSearchResponseDto.of(searchResult);
    }

    @GetMapping("/keywords")
    public List<BlogSearchKeywordItemResponseDto> getPopularBlogSearchKeywords() {
        List<BlogSearchKeyword> keywordList = blogSearchService.getPopularKeywords(10);
        return keywordList.stream().map(BlogSearchKeywordItemResponseDto::of).collect(Collectors.toList());
    }
}
