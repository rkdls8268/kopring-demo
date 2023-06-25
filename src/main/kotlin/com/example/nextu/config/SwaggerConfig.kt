package com.example.nextu.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI().components(Components()).info(Info())
    }

    fun apiInfo() = Info()
        .title("Springdoc 테스트")
        .description("Springdoc 을 활용한 Swagger ui 테스트")
        .version("1.0.0")
}