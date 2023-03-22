package com.example.blogsearch.adapter.out.client.naver;

import com.example.blogsearch.adapter.out.client.naver.dto.NaverSearchResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naver-client", url="${external-client.naver.url}")
public interface NaverClient {
    @GetMapping(value = "/v1/search/blog.json",
            produces = "application/json",
            headers = {"X-Naver-Client-Id=${external-client.naver.client-id}", "X-Naver-Client-Secret=${external-client.naver.client-secret}"})
    NaverSearchResponseDto searchBlog(@RequestParam String query, @RequestParam Integer display, @RequestParam Integer start, @RequestParam String sort);
}
