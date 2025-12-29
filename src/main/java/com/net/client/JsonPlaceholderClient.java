package com.net.client;

import com.net.config.HttpFeignClientConfig;
import com.net.dto.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "jsonplaceholder",
        url = "${jsonplaceholder.url:https://jsonplaceholder.typicode.com}",
        configuration = HttpFeignClientConfig.class
)
public interface JsonPlaceholderClient {

    @GetMapping("/posts/{id}")
    Post getPostById(@PathVariable("id") Long id);
}
