package com.example.blogsearch.adapter.out.client.daum;

import com.example.blogsearch.adapter.out.client.daum.dto.SearchResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "daum-client", url="${external-client.daum.url}")
public interface DaumClient {
    @GetMapping(value = "/v2/search/blog", produces = "application/json", headers = "Authorization=${external-client.daum.authorization}")
    SearchResponseDto searchBlog(@RequestParam String query, @RequestParam String sort, @RequestParam Integer page, @RequestParam Integer size);
}
