package com.example.nextu.todo.repository

import com.example.nextu.todo.entity.UserTodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserTodoRepository: JpaRepository<UserTodoEntity, Int> {
    fun findOneByUserId(userId: Int): UserTodoEntity
}