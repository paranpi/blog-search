package com.example.blogsearch.adapter.in.web;

import com.example.blogsearch.adapter.in.web.dto.BlogSearchRequestDto;
import com.example.blogsearch.adapter.in.web.dto.BlogSearchResponseDto;
import com.example.blogsearch.application.service.BlogSearchService;
import com.example.blogsearch.domain.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = "/v1/search", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@RestController
public class SearchController {
    private final BlogSearchService blogSearchService;
    @GetMapping("/blog")
    public BlogSearchResponseDto searchBlogs(@Valid BlogSearchRequestDto requestDto) {
        BlogSearchResult searchResult = blogSearchService.searchBlogPosts(requestDto.getQuery(), requestDto.getPage(), requestDto.getSize(), requestDto.getSort());
        return BlogSearchResponseDto.of(searchResult);
    }
}
