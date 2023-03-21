package com.example.blogsearch.adapter.out.client.daum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchResponseMetaDto {
    @JsonProperty("total_count")
    Integer totalCount;
    @JsonProperty("pageable_count")
    Integer pageableCount;
    @JsonProperty("is_end")
    Boolean isEnd;
}
