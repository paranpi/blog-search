package com.example.blogsearch.adapter.out.client.daum.dto;

import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchResultItem;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchResponseDtoTest {

    @Test
    void toBlogSearchResultTest() {
        //Given
        SearchResponseMetaDto metaDto = SearchResponseMetaDto.builder()
                .totalCount(100)
                .pageableCount(10)
                .isEnd(false)
                .build();

        SearchResponseDocumentDto searchResponseDocumentDto = SearchResponseDocumentDto.builder()
                .title("blog post title")
                .contents("contents is empty")
                .url("http://testtest.test.ts")
                .datetime(OffsetDateTime.now())
                .build();

        SearchResponseDto dto = SearchResponseDto.builder()
                .meta(metaDto)
                .documents(List.of(searchResponseDocumentDto))
                .build();
        //When
        BlogSearchResult result = dto.toBlogSearchResult();

        //Then
        assertEquals(metaDto.getTotalCount(), result.getMeta().getTotalCount());
        assertEquals(metaDto.getPageableCount(), result.getMeta().getPageCount());
        BlogSearchResultItem firstItem = result.getDataList().get(0);
        assertEquals(searchResponseDocumentDto.getTitle(), firstItem.getTitle());
        assertEquals(searchResponseDocumentDto.getContents(), firstItem.getContents());
        assertEquals(searchResponseDocumentDto.getUrl(), firstItem.getUrl());
    }
}