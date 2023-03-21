package com.example.blogsearch.adapter.out.client.daum.dto;

import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchResultItem;
import com.example.blogsearch.domain.BlogSearchResultMeta;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class SearchResponseDto {
    private SearchResponseMetaDto meta;
    private List<SearchResponseDocumentDto> documents;

    //TODO: change to using MapStruct
    public BlogSearchResult toBlogSearchResult(){
        BlogSearchResultMeta resultMeta = BlogSearchResultMeta.builder()
                .totalCount(meta.totalCount)
                .pageCount(meta.pageableCount)
                .build();

        List<BlogSearchResultItem> dataList = new ArrayList<>();
        for (SearchResponseDocumentDto item : getDocuments()) {
            BlogSearchResultItem build = BlogSearchResultItem.builder()
                    .title(item.getTitle())
                    .contents(item.getContents())
                    .url(item.getUrl())
                    .datetime(item.getDatetime())
                    .build();
            dataList.add(build);
        }
        return BlogSearchResult.builder()
                .meta(resultMeta)
                .dataList(dataList)
                .build();
    }
}
