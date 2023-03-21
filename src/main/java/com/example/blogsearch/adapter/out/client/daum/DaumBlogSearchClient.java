package com.example.blogsearch.adapter.out.client.daum;

import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.domain.BlogSearchResult;
import org.springframework.stereotype.Component;

@Component
public class DaumBlogSearchClient implements LoadBlogPort {
    DaumClient daumClient;
    @Override
    public BlogSearchResult loadByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortType) {
        SearchResponseDto responseDto = daumClient.searchBlog(keyword, sortType, pageNumber, pageSize);
        return responseDto.toBlogSearchResult();
    }
}
