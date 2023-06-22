package com.example.nextu.todo.repository

import com.example.nextu.todo.entity.UserTodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserTodoRepository: JpaRepository<UserTodoEntity, Int> {
    fun existsByUserIdAndTodoId(userId: Int, todoId: Int): Boolean
    fun findAllByUserId(userId: Int): List<UserTodoEntity>
}