package com.example.nextu.todo.service

import com.example.nextu.todo.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository
) {
    fun getUserId(): Int {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        return userRepository.findOneByEmail(userDetails.username).id
    }
}