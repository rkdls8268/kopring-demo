package com.example.nextu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class NextuApplication

fun main(args: Array<String>) {
	runApplication<NextuApplication>(*args)
}
