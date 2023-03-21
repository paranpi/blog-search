package com.example.blogsearch.application.service;

import com.example.blogsearch.application.port.in.SearchBlogQuery;
import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.domain.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements SearchBlogQuery {
    private final LoadBlogPort daumBlogSearchClient;

    private final LoadBlogPort naverBlogSearchClient;

    @Override
    public BlogSearchResult searchBlogPosts(String keyword, Integer pageNumber, Integer pageSize, String sortType) {
        try {
            return daumBlogSearchClient.loadByKeyword(keyword, pageNumber, pageSize, sortType);
        } catch (Exception e) {
            //fallback
            return naverBlogSearchClient.loadByKeyword(keyword, pageNumber, pageSize, sortType);
        }
    }
}
