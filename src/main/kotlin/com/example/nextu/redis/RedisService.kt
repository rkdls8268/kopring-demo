package com.example.nextu.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    val redisTemplate: RedisTemplate<String, Any>
) {
    fun getKey(key: String): Any? {
        val valueOperations = redisTemplate.opsForValue()
        return valueOperations.get(key)
    }

    fun <T> setKey(key: String, value: T) {
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.set(key, value as Any)
    }

    fun deleteKey(key: String) {
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.getAndDelete(key)
    }
}