package com.net.config;

import feign.Client;
import feign.hc5.ApacheHttp5Client;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.security.cert.X509Certificate;

public class FeignSslConfig {

    @Bean
    public Client feignClient() throws Exception {

        // Locate the client certificate file (mTLS Keystore)
        File keyStoreFile = ResourceUtils.getFile("file:./certs/badssl.com-client.p12");

        SSLContext sslContext = SSLContextBuilder.create()
                // 1. Bypass server certificate validation (Trust All strategy for local testing)
                .loadTrustMaterial(new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] chain, String authType) {
                        return true;
                    }
                })
                // 2. Load the client identity (Provide file, keystore password, and private key password)
                .loadKeyMaterial(keyStoreFile, "badssl.com".toCharArray(), "badssl.com".toCharArray())
                .build();

        SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                sslContext,
                NoopHostnameVerifier.INSTANCE
        );

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(
                        PoolingHttpClientConnectionManagerBuilder.create()
                                .setSSLSocketFactory(sslSocketFactory)
                                .build()
                )
                .build();

        return new ApacheHttp5Client(httpClient);
    }
}