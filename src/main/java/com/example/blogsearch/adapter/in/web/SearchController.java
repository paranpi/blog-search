package com.example.blogsearch.adapter.in.web;

import com.example.blogsearch.adapter.in.web.dto.BlogSearchKeywordItemResponseDto;
import com.example.blogsearch.adapter.in.web.dto.BlogSearchResponseDto;
import com.example.blogsearch.application.service.BlogSearchService;
import com.example.blogsearch.domain.BlogSearchKeyword;
import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(value = "/v1/blog", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@RestController
@Validated
public class SearchController {
    private final BlogSearchService blogSearchService;
    @GetMapping("/search")
    public BlogSearchResponseDto searchBlogs(@RequestParam String keyword,
                                             @RequestParam(defaultValue = "1") @Min(1) @Max(50) Integer pageNumber,
                                             @RequestParam(defaultValue = "10") @Min(1) @Max(50) Integer pageSize,
                                             @RequestParam(defaultValue = "ACCURACY") String sortType
    ) {
        BlogSearchResult searchResult = blogSearchService.searchBlogPosts(keyword, pageNumber, pageSize, BlogSearchSortType.of(sortType));
        return BlogSearchResponseDto.of(searchResult);
    }

    @GetMapping("/keywords")
    public List<BlogSearchKeywordItemResponseDto> getPopularBlogSearchKeywords() {
        List<BlogSearchKeyword> keywordList = blogSearchService.getPopularKeywords(10);
        return keywordList.stream().map(BlogSearchKeywordItemResponseDto::of).collect(Collectors.toList());
    }
}
