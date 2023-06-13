package com.example.nextu.todo.dto

import com.example.nextu.todo.entity.TodoEntity
import java.time.LocalDateTime

class TodoDTO {
    class CreateTodoDTO(
        private val title: String,
        private val memo: String?
    ) {
        fun toEntity(): TodoEntity {
            return TodoEntity(
                title = title,
                memo = memo,
                checked = false,
            )
        }
    }

    class GetTodoDTO(
        val id: Int,
        val title: String,
        val memo: String?,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?
    )
}