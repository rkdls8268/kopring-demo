package com.example.nextu.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class RedisService(
    val redisTemplate: RedisTemplate<String, Any>,
    @Value("\${jwt.refreshTokenExpiresIn}")
    val refreshTokenExpiresIn: Long
) {
    fun getKey(key: String): Any? {
        val valueOperations = redisTemplate.opsForValue()
        return valueOperations.get(key)
    }

    fun <T> setKey(key: RedisKey, value: T) {
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.set(key.toString(), value as Any, refreshTokenExpiresIn, TimeUnit.SECONDS)
    }

    fun deleteKey(key: String) {
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.getAndDelete(key)
    }
}