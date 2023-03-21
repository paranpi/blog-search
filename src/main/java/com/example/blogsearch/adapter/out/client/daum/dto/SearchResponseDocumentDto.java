package com.example.blogsearch.adapter.out.client.daum.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.OffsetDateTime;

@Getter
@Builder
public class SearchResponseDocumentDto {
    private String title;
    private String contents;
    private String url;
    private OffsetDateTime datetime;
}
