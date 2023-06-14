package com.example.nextu

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class EncoderTest {
    @Autowired
    val encoder = BCryptPasswordEncoder()

    @Test
    fun encoder() {
        val encoded = encoder.encode("12341234")
        println(encoded)
    }

}