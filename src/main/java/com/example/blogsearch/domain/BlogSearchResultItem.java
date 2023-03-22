package com.example.blogsearch.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class BlogSearchResultItem {
    String title;
    String contents;
    String url;
    String blogname;
    String thumbnail;
    OffsetDateTime datetime;
}
