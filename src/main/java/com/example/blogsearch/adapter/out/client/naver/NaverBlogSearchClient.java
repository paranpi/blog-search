package com.example.blogsearch.adapter.out.client.naver;

import com.example.blogsearch.adapter.out.client.naver.dto.NaverSearchResponseDto;
import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverBlogSearchClient implements LoadBlogPort {
    private final NaverClient naverClient;
    @Override
    public BlogSearchResult loadByKeyword(String keyword, Integer pageNumber, Integer pageSize, BlogSearchSortType sortType) {
        String sort;
        if(sortType == BlogSearchSortType.RECENCY) {
            sort = "date";
        } else {
            sort = "sim";
        }
        NaverSearchResponseDto result = naverClient.searchBlog(keyword, pageSize,pageNumber, sort);
        return result.toBlogSearchResult();
    }
}
