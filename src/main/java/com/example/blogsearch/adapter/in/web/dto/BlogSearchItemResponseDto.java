package com.example.blogsearch.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class BlogSearchItemResponseDto {
    String title;
    String contents;
    String url;
    OffsetDateTime datetime;
}
