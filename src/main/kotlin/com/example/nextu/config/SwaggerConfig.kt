package com.example.nextu.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * https://springdoc.org/
 */
@Configuration
class SwaggerConfig {
    private val jwtSchemeName = "auth"

    @Bean
    fun openApi(): OpenAPI {
        // API 요청헤더에 인증 정보 포함
        return OpenAPI()
            .addSecurityItem(securityRequirement())
            .components(components())
            .info(apiInfo())
    }

    private fun apiInfo() = Info()
        .title("Springdoc 테스트")
        .description("Springdoc 을 활용한 Swagger ui 테스트")
        .version("1.0.0")

    private fun components(): Components {
        val securityScheme = SecurityScheme()
            .name(jwtSchemeName)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")

        // security schemes 등록
        return Components()
            .addSecuritySchemes(jwtSchemeName, securityScheme)
    }

    // API 요청헤더에 인증 정보 포함
    private fun securityRequirement() = SecurityRequirement().addList(jwtSchemeName)
}
