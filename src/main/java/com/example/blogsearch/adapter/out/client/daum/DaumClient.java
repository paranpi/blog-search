package com.example.blogsearch.adapter.out.client.daum;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "daumBlogApi", url="https://dapi.kakao.com")
public interface DaumClient {
    @GetMapping("/v2/search/blog")
    SearchResponseDto searchBlog(@RequestParam String query, @RequestParam String sort, @RequestParam Integer page, @RequestParam Integer size);
}
