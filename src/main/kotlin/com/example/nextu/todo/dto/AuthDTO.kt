package com.example.nextu.todo.dto

class AuthDTO{
    class TokenDTO(
        val accessToken: String,
        val refreshToken: String
    )

    class LoginRequestDTO(
        val email: String,
        val password: String,
    )
}
