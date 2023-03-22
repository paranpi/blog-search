package com.example.blogsearch.application.service;

import com.example.blogsearch.application.port.in.GetPopularKeywordQuery;
import com.example.blogsearch.application.port.in.SearchBlogQuery;
import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.application.port.out.LoadKeywordPort;
import com.example.blogsearch.application.port.out.SaveKeywordPort;
import com.example.blogsearch.domain.BlogSearchKeyword;
import com.example.blogsearch.domain.BlogSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogSearchService implements SearchBlogQuery, GetPopularKeywordQuery {
    private final LoadBlogPort daumBlogSearchClient;

    private final LoadBlogPort naverBlogSearchClient;

    private final LoadKeywordPort loadKeywordPort;

    private final SaveKeywordPort saveKeywordPort;

    @Override
    @Transactional
    public BlogSearchResult searchBlogPosts(String keyword, Integer pageNumber, Integer pageSize, String sortType) {
        try {
            return daumBlogSearchClient.loadByKeyword(keyword, pageNumber, pageSize, sortType);
        } catch (Exception e) {
            //fallback
            return naverBlogSearchClient.loadByKeyword(keyword, pageNumber, pageSize, sortType);
        }finally {
            increaseSearchCount(keyword);
        }
    }


    private void increaseSearchCount(String keyword) {
        BlogSearchKeyword blogSearchKeyword = loadKeywordPort.loadSearchKeyword(keyword);
        blogSearchKeyword.increaseSearchCount();
        saveKeywordPort.saveSearchKeyword(blogSearchKeyword);
    }
    @Override
    public List<BlogSearchKeyword> getPopularKeywords(Integer limit) {
        return loadKeywordPort.loadKeywordsSortByPopular(limit);
    }
}
