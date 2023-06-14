package com.example.nextu.controller

import com.example.nextu.auth.TokenProvider
import com.example.nextu.todo.dto.AuthDTO
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val tokenProvider: TokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequestDTO: AuthDTO.LoginRequestDTO
    ): AuthDTO.TokenDTO {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginRequestDTO.email, loginRequestDTO.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication

        val token = tokenProvider.createToken(authentication)
        return AuthDTO.TokenDTO(
            accessToken = token
        )
    }
}