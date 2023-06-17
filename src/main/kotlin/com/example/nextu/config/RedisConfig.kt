package com.example.nextu.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.StringRedisSerializer




@Configuration
@EnableRedisRepositories
class RedisConfig(
    @Value(value = "\${spring.redis.port}")
    val port: Int,
    @Value(value = "\${spring.redis.host}")
    val host: String,
    @Value(value = "\${spring.redis.password}")
    val password: String,
) {
//    private val redisProperties = RedisProperties()

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun <T>redisTemplate(): RedisTemplate<String, T> {
        val redisTemplate = RedisTemplate<String, T>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        return redisTemplate
    }
}