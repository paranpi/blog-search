package com.example.blogsearch.adapter.out.client.daum;

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

    public BlogSearchResult toBlogSearchResult(){
        BlogSearchResultMeta resultMeta = BlogSearchResultMeta.builder()
                .totalCount(meta.totalCount)
                .pageCount(meta.pageableCount)
                .build();

        List<BlogSearchResultItem> dataList = new ArrayList<>();
        for (SearchResponseDocumentDto item : this.documents) {
            BlogSearchResultItem build = BlogSearchResultItem.builder()
                    .blogname(item.getBlogname())
                    .title(item.getTitle())
                    .contents(item.getContents())
                    .url(item.getUrl())
                    .thumbnail(item.getThumbnail())
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
