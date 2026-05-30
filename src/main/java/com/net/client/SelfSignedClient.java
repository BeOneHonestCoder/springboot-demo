package com.net.client;

import com.net.config.FeignSslConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "self-signed-client",
        url = "${self-signed.url:https://client.badssl.com/}",
        configuration = FeignSslConfig.class
)
public interface SelfSignedClient {

    @GetMapping("/")
    String testSelfSigned();
}
