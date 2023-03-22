package com.example.blogsearch.application.service;

import com.example.blogsearch.application.port.in.GetPopularKeyword;
import com.example.blogsearch.application.port.in.SearchBlog;
import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.application.port.out.LoadKeywordPort;
import com.example.blogsearch.application.port.out.SaveKeywordPort;
import com.example.blogsearch.domain.BlogSearchKeyword;
import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchSortType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogSearchService implements SearchBlog, GetPopularKeyword {
    private final LoadBlogPort blogSearchClient;

    private final LoadBlogPort fallbackBlogSearchClient;

    private final LoadKeywordPort loadKeywordPort;

    private final SaveKeywordPort saveKeywordPort;

    public BlogSearchService(@Qualifier("daumBlogSearchClient") LoadBlogPort blogSearchClient,
                             @Qualifier("naverBlogSearchClient") LoadBlogPort fallbackBlogSearchClient,
                             LoadKeywordPort loadKeywordPort, SaveKeywordPort saveKeywordPort) {
        this.blogSearchClient = blogSearchClient;
        this.fallbackBlogSearchClient = fallbackBlogSearchClient;
        this.loadKeywordPort = loadKeywordPort;
        this.saveKeywordPort = saveKeywordPort;
    }

    @Override
    @Transactional
    public BlogSearchResult searchBlogPosts(String keyword, Integer pageNumber, Integer pageSize, BlogSearchSortType sortType) {
        try {
            return blogSearchClient.loadByKeyword(keyword, pageNumber, pageSize, sortType);
        } catch (Exception e) {
            //fallback
            return fallbackBlogSearchClient.loadByKeyword(keyword, pageNumber, pageSize, sortType);
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
