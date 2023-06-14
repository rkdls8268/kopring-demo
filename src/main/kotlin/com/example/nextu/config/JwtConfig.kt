package com.example.nextu.config

import com.example.nextu.auth.JwtAuthenticationFilter
import com.example.nextu.auth.TokenProvider
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

// TokenProvider 를 주입 받아서 JwtAuthenticationFilter 를 Security 로직에 등록하기 위한 클래스
@Configuration
class JwtConfig(
    private val tokenProvider: TokenProvider
): SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(http: HttpSecurity) {
        val customFilter = JwtAuthenticationFilter(tokenProvider)
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}