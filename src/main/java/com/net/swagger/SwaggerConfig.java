package com.net.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"springdoc.api-docs.enabled"}, havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Restful API")
                .description("Restful API");
    }

    @Bean
    public GroupedOpenApi businessApi() {
        return GroupedOpenApi.builder()
                .group("business")
                .pathsToMatch("/**")
                .pathsToExclude("/api/ff4j/**")
                .build();
    }

    @Bean
    public GroupedOpenApi ff4jApi() {
        return GroupedOpenApi.builder()
                .group("ff4j")
                .pathsToMatch("/api/ff4j/**")
                .build();
    }

}
