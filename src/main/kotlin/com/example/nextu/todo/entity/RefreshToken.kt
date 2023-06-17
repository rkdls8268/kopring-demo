package com.example.nextu.todo.entity

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash("refreshToken")
class RefreshToken(
    @Id
    val id: Int = 0,
    val userId: Int,
    val refreshToken: String
) {
}