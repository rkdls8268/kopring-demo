package com.example.nextu.controller

import com.example.nextu.auth.TokenProvider
import com.example.nextu.redis.RedisService
import com.example.nextu.todo.dto.AuthDTO
import com.example.nextu.todo.service.AuthService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val redisService: RedisService,
    private val authService: AuthService,

) {
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequestDTO: AuthDTO.LoginRequestDTO
    ): AuthDTO.TokenDTO {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginRequestDTO.email, loginRequestDTO.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication

        val token = tokenProvider.createToken(authentication)
        redisService.setKey("refreshToken", token.refreshToken)
//        authService.saveRefreshToken(1, token.refreshToken)
        return AuthDTO.TokenDTO(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken
        )
    }

    @PostMapping("/refresh/{userId}")
    fun refresh(
        @PathVariable userId: Int,
    ): AuthDTO.TokenDTO {
        // userId 로 redis 에서 refreshToken 찾아오기
        val refreshToken = redisService.getKey("refreshToken")
        val accessToken: String
        if (refreshToken != null) {
            accessToken = tokenProvider.reissueToken(refreshToken as String)
            val authentication = tokenProvider.getAuthentication(accessToken)
            SecurityContextHolder.getContext().authentication = authentication
        } else {
            throw Exception("refreshToken 이 존재하지 않습니다.")
        }
        return AuthDTO.TokenDTO(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}