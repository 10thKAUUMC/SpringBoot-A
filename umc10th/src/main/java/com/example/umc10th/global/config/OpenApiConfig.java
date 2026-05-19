package com.example.umc10th.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String JWT_SCHEME_NAME = "JWT Auth";

    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(JWT_SCHEME_NAME);

        Components components = new Components()
                .addSecuritySchemes(JWT_SCHEME_NAME, new SecurityScheme()
                        .name(JWT_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .info(new Info()
                        .title("UMC 10th API")
                        .description("""
                                스프링부트 기반 미션·가게·리뷰·회원 API입니다. \
                                공통 응답 형식은 `ApiResponse`(`isSuccess`, `code`, `message`, `result`)를 따릅니다. \
                                Private API는 우측 상단 Authorize에 로그인으로 받은 JWT를 입력하세요."""
                        )
                        .version("1.0.0"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
