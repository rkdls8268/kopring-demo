package com.example.nextu.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {
    @RequestMapping("/")
    fun getController(): String {
        return "hello"
    }
}