package com.example.blogsearch.adapter.out.client.naver.dto;

import com.example.blogsearch.domain.BlogSearchResult;
import com.example.blogsearch.domain.BlogSearchResultItem;
import com.example.blogsearch.domain.BlogSearchResultMeta;
import lombok.Builder;
import lombok.Getter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class NaverSearchResponseDto {
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;

    private List<NaverSearchResponseItemDto> items;
    public BlogSearchResult toBlogSearchResult(){
        BlogSearchResultMeta meta = BlogSearchResultMeta.builder()
                .totalCount(total)
                .build();

        List<BlogSearchResultItem> itemList = new ArrayList<>();
        for(NaverSearchResponseItemDto item: items) {
            LocalDate localDate = LocalDate.parse(item.getPostdate(), DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDateTime localDateTime = localDate.atStartOfDay();
            ZoneOffset offset = ZoneId.of("Asia/Seoul").getRules().getOffset(localDateTime);
            OffsetDateTime dateTime = OffsetDateTime.of(localDateTime, offset);

            BlogSearchResultItem resultItem = BlogSearchResultItem.builder()
                    .title(item.getTitle())
                    .contents(item.getDescription())
                    .url(item.getBloggerlink())
                    .blogname(item.getBloggername())
                    .datetime(dateTime)
                    .build();
            itemList.add(resultItem);
        }
        return BlogSearchResult.builder()
                .meta(meta)
                .dataList(itemList)
                .build();
    }
}
