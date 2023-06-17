package com.example.nextu.todo.repository

import com.example.nextu.todo.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, Int> {
}