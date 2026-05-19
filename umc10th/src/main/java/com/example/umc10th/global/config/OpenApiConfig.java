package com.example.umc10th.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("UMC 10th API")
                        .description("""
                                스프링부트 기반 미션·가게·리뷰·회원 API입니다. \
                                공통 응답 형식은 `ApiResponse`(`isSuccess`, `code`, `message`, `result`)를 따릅니다."""
                        )
                        .version("1.0"));
    }
}
