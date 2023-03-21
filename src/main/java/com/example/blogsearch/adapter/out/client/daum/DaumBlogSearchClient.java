package com.example.blogsearch.adapter.out.client.daum;

import com.example.blogsearch.adapter.out.client.daum.dto.SearchResponseDto;
import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.domain.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DaumBlogSearchClient implements LoadBlogPort {
    private final DaumClient daumClient;
    @Override
    public BlogSearchResult loadByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortType) {
        SearchResponseDto responseDto = daumClient.searchBlog(keyword, sortType, pageNumber, pageSize);
        return responseDto.toBlogSearchResult();
    }
}
