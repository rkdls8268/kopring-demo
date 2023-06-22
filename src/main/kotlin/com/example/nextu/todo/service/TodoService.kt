package com.example.nextu.todo.service

import com.example.nextu.todo.dto.TodoDTO
import com.example.nextu.todo.entity.UserTodoEntity
import com.example.nextu.todo.repository.TodoRepository
import com.example.nextu.todo.repository.UserRepository
import com.example.nextu.todo.repository.UserTodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val userRepository: UserRepository,
    private val todoRepository: TodoRepository,
    private val userTodoRepository: UserTodoRepository,
) {
    fun getTodos(): List<TodoDTO.GetTodoDTO> {
        val todoList = todoRepository.findAll()
        return todoList.map {
            TodoDTO.GetTodoDTO(
                id = it.id,
                title = it.title,
                memo = it.memo,
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    fun createTodo(userId: Int, createTodoDTO: TodoDTO.CreateTodoDTO) {
        val user = userRepository.findByIdOrNull(userId) ?: throw Exception("유저가 존재하지 않습니다.")
        val todo = todoRepository.save(createTodoDTO.toEntity())
        val userTodo = UserTodoEntity(
            user = user,
            todo = todo,
        )
        userTodoRepository.save(userTodo)
    }

    fun checkTodo(todoId: Int) {
        val todoEntity = todoRepository.findByIdOrNull(todoId) ?: throw Exception("todo 가 존재하지 않습니다.")
        todoEntity.checked = !todoEntity.checked
        todoRepository.save(todoEntity)
    }
}