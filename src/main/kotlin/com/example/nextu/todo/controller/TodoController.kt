package com.example.nextu.todo.controller

import com.example.nextu.todo.dto.TodoDTO
import com.example.nextu.todo.service.TodoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController(
    private var todoService: TodoService
) {
    @GetMapping("/todos")
    fun getTodos(): List<TodoDTO.GetTodoDTO> {
        return todoService.getTodos()
    }

    @PostMapping("todos")
    fun createTodo(
        @RequestBody createTodoDTO: TodoDTO.CreateTodoDTO
    ) {
        todoService.createTodo(createTodoDTO)
    }
}