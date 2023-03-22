package com.example.blogsearch.adapter.in.web.dto;

import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchResultItem;
import com.example.blogsearch.domain.BlogSearchResultMeta;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class BlogSearchResponseDto {
    private BlogSearchMetaResponseDto meta;
    private List<BlogSearchItemResponseDto> dataList;

    public static BlogSearchResponseDto of(BlogSearchResult result) {
        BlogSearchResultMeta resultMeta = result.getMeta();
        BlogSearchMetaResponseDto meta = BlogSearchMetaResponseDto.builder()
                .totalCount(resultMeta.getTotalCount())
                .build();

        List<BlogSearchItemResponseDto> dataList = new ArrayList<>();
        List<BlogSearchResultItem> resultDataList = result.getDataList();
        for(BlogSearchResultItem resultItem: resultDataList) {
            BlogSearchItemResponseDto blogSearchItemResponseDto = BlogSearchItemResponseDto.builder()
                    .title(resultItem.getTitle())
                    .contents(resultItem.getContents())
                    .url(resultItem.getUrl())
                    .datetime(resultItem.getDatetime())
                    .build();
            dataList.add(blogSearchItemResponseDto);
        }
        return BlogSearchResponseDto.builder()
                .meta(meta)
                .dataList(dataList)
                .build();
    }
}
