package com.example.nextu.todo.service

import com.example.nextu.todo.repository.UserTodoRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userTodoRepository: UserTodoRepository
) {
    fun isRightUser(userId: Int, todoId: Int): Boolean {
        return todoId == userTodoRepository.findOneByUserId(userId).todo.id
    }
}