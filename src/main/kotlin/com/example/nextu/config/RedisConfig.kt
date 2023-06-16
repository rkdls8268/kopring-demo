package com.example.nextu.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig(
//    @Value(value = "\${spring.redis.port}") port: String,
//    @Value(value = "\${spring.redis.host}") host: Int,
    @Value(value = "\${spring.redis.password}") password: String,
) {
    private val redisProperties = RedisProperties()

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(redisProperties.host, redisProperties.port)
    }

    @Bean
    fun <T>redisTemplate(): RedisTemplate<String, T> {
        val redisTemplate = RedisTemplate<String, T>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
    }
}