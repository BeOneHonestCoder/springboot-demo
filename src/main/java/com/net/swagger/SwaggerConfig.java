package com.net.swagger;

import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.Scopes;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = {"springdoc.api-docs.enabled"}, havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "client_credentials_auth";

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String auth0IssuerUri;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl(auth0IssuerUri + "authorize")
                                                .tokenUrl(auth0IssuerUri + "oauth/token")
                                                .scopes(new Scopes()
                                                        .addString("openid", "OpenID (Required)")
                                                        .addString("profile", "Profile (Required)")
                                                        .addString("email", "Email")
                                                        .addString("USER", "User Access")
                                                        .addString("ADMIN", "Admin Access")
                                                )
                                        )
                                )
                        )
                );
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

}
