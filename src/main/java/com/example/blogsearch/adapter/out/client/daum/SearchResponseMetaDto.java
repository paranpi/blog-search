package com.example.blogsearch.adapter.out.client.daum;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchResponseMetaDto {
    Integer totalCount;
    Integer pageableCount;
    Boolean isEnd;
}
