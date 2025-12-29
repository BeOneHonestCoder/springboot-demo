package com.net.client;

import com.net.dto.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// 启动 WireMock 并监听随机端口
@AutoConfigureWireMock(port = 0)
// 激活测试配置，用于覆盖 Feign 的真实 URL
@ActiveProfiles("test")
class JsonPlaceholderClientTest {

    @Autowired
    private JsonPlaceholderClient jsonPlaceholderClient;

    @Test
    void testGetPostById_ShouldReturnMockedPost() {
        // 1. Stubbing: 定义 WireMock 的响应
        stubFor(get(urlEqualTo("/posts/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"id\": 1, \"userId\": 10, \"title\": \"WireMock Test\", \"body\": \"Hello Feign!\"}")));

        // 2. Execution: 通过 FeignClient 发起调用
        Post post = jsonPlaceholderClient.getPostById(1L);

        // 3. Assertion: 验证结果
        assertNotNull(post);
        assertEquals(1L, post.getId());
        assertEquals("WireMock Test", post.getTitle());
        assertEquals("Hello Feign!", post.getBody());

        // 验证请求确实发送到了 WireMock
        verify(getRequestedFor(urlEqualTo("/posts/1")));
    }
}
