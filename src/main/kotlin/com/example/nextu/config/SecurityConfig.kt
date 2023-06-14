package com.example.nextu.config;

import com.example.nextu.auth.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

// SecurityConfig: passwordEncoder 생성, JwtAuthenticationFilter 등록, 로그인 및 회원가입 요청 URL 제외한 나머지는 인증 받아야 요청 가능하도록 권한 설정
// webSecurityConfigureAdapter() 는 deprecated
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenProvider: TokenProvider
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            // 토큰을 사용하기 때문에 csrf 설정 disable
            .csrf().disable()
            // 예외처리
                .exceptionHandling()
            // 세션 사용하지 않기 때문에 세션 설정 STATELESS
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // 토큰이 없는 상태에서 요청이 들어오는 API 들은 permitAll
            .and()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
            // JwtFilter를 addFilterBefore로 등록했던 jwtSecurityConfig 클래스 적용
            // 여기서 직접 addFilter 해주거나 config 등록
            .and()
                .apply(JwtConfig(tokenProvider))
            .and().build()
    }
}
