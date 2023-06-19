package com.example.nextu.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    val redisTemplate: RedisTemplate<String, Any>
) {
    // TODO key 에 userId 값 추가
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