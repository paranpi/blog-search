package com.example.blogsearch.application.service;

import com.example.blogsearch.adapter.out.client.DaumBlogSearchClient;
import com.example.blogsearch.adapter.out.client.NaverBlogSearchClient;
import com.example.blogsearch.application.port.out.LoadBlogPort;
import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchResultItem;
import com.example.blogsearch.domain.BlogSearchResultMeta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class BlogSearchServiceTest {
    @Autowired
    BlogSearchService blogSearchService;

    @MockBean(classes = DaumBlogSearchClient.class)
    LoadBlogPort daumBlogSearchClient;

    @MockBean(classes = NaverBlogSearchClient.class)
    LoadBlogPort naverBlogSearchClient;

    @Test
    void searchBlogPostTest() {
        //given
        BlogSearchResultItem item = BlogSearchResultItem.builder()
                .title("작은 <b>집</b> <b>짓기</b> 기본컨셉 - <b>집</b><b>짓기</b> 초기구상하기")
                .contents("이 점은 <b>집</b>을 지으면서 고민해보아야 한다. 하지만, 금액에 대한 가성비 대비 크게 문제되지 않을 부분이라 생각하여 설계로 극복하자고 생각했다. 전체 <b>집</b><b>짓기</b>의 기본방향은 크게 세 가지이다. 우선은 여가의 영역 증대이다. 현대 시대 일도 중요하지만, 여가시간 <b>집</b>에서 어떻게 보내느냐가 중요하니깐 이를 기본적...")
                .url("https://brunch.co.kr/@tourism/9")
                .blogname("정란수의 브런치")
                .thumbnail("http://search3.kakaocdn.net/argon/130x130_85_c/7r6ygzbvBDc")
                .datetime(LocalDateTime.parse("2017-05-07T18:50:07.000+09:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
        BlogSearchResultMeta meta = BlogSearchResultMeta.builder().totalCount(1).pageCount(1).build();
        BlogSearchResult searchResult = BlogSearchResult.builder().meta(meta).documents(List.of(item)).build();
        given(daumBlogSearchClient.loadByKeyword("test", 1, 10, "accuracy"))
                .willReturn(searchResult);

        //when
        BlogSearchResult result = blogSearchService.searchBlogPosts("test", 1, 10, "accuracy");

        //then
        assertNotNull(result);
        assertNotNull(result.getDocuments());
        assertTrue(result.getDocuments().size() > 0);
        assertNotNull(result.getMeta());
    }

    @Test
    void searchBlogPostFallbackTest() {
        BlogSearchResultItem item = BlogSearchResultItem.builder()
                .title("블로그 포스트의 제목. 제목에서 검색어와 일치하는 부분은 <b> 태그로 감싸져 있습니다.")
                .contents("블로그 포스트의 내용을 요약한 패시지 정보. 패시지 정보에서 검색어와 일치하는 부분은 <b> 태그로 감싸져 있습니다.")
                .url("블로그 포스트가 있는 블로그의 주소")
                .blogname("정란수의 브런치")
                .datetime(LocalDateTime.parse("2017-05-07T18:50:07.000+09:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
                .build();
        BlogSearchResultMeta meta = BlogSearchResultMeta.builder().totalCount(1).pageCount(1).build();
        BlogSearchResult searchResult = BlogSearchResult.builder().meta(meta).documents(List.of(item)).build();

        given(daumBlogSearchClient.loadByKeyword("fallback", 1, 10, "accuracy"))
                .willAnswer(invocation -> {
                    throw new Exception("502 Bad Gateway");
                });
        given(naverBlogSearchClient.loadByKeyword("fallback", 1, 10, "accuracy"))
                .willReturn(searchResult);
        BlogSearchResult result = blogSearchService.searchBlogPosts("fallback", 1, 10, "accuracy");
        assertNotNull(result);
        assertNotNull(result.getDocuments());
        assertTrue(result.getDocuments().size() > 0);
        assertEquals("정란수의 브런치", result.getDocuments().get(0).getBlogname());
        assertNotNull(result.getMeta());
    }
}
