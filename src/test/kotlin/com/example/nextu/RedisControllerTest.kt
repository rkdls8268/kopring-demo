package com.example.nextu

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class RedisControllerTest @Autowired constructor(
    val redisTemplate: RedisTemplate<String, Any>
) {
    @Test
    fun getKey() {
        val valueOperations = redisTemplate.opsForValue()
        valueOperations.set("name", "gain")

        val name = valueOperations.get("name")
        Assertions.assertEquals(name, "gain")
    }
}