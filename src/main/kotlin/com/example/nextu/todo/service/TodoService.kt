package com.example.nextu.todo.service

import com.example.nextu.todo.dto.TodoDTO
import com.example.nextu.todo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
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

    fun createTodo(createTodoDTO: TodoDTO.CreateTodoDTO) {
        todoRepository.save(createTodoDTO.toEntity())
    }

    fun checkTodo(todoId: Int) {
        // todoEntity 가 null 일 경우 ? -> throw exception()
        val todoEntity = todoRepository.getById(todoId)

        todoEntity.checked = !todoEntity.checked
        todoRepository.save(todoEntity)
    }
}