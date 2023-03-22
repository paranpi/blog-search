package com.example.blogsearch.adapter.out.client.naver.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NaverSearchResponseItemDto {
    private String title;
    private String link;
    private String description;
    private String bloggername;
    private String bloggerlink;
    private String postdate;
}
