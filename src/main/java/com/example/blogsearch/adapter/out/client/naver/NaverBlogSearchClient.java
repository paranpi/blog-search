package com.example.blogsearch.adapter.out.client.naver;

import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.domain.BlogSearchResult;
import org.springframework.stereotype.Component;

@Component
public class NaverBlogSearchClient implements LoadBlogPort {
    //FeignClient???
    @Override
    public BlogSearchResult loadByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortType) {
        return null;
    }
}