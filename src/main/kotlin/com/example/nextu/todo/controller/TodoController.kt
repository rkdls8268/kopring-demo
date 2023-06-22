package com.example.nextu.todo.controller

import com.example.nextu.todo.dto.TodoDTO
import com.example.nextu.todo.service.AuthService
import com.example.nextu.todo.service.TodoService
import com.example.nextu.todo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class TodoController(
    private val todoService: TodoService,
    private val userService: UserService,
    private val authService: AuthService
) {
    @GetMapping("/todos")
    fun getTodos(): List<TodoDTO.GetTodoDTO> {
        return todoService.getTodos()
    }

    @PostMapping("todos")
    fun createTodo(
        @RequestBody createTodoDTO: TodoDTO.CreateTodoDTO
    ) {
        val userId = authService.getUserId()
        todoService.createTodo(userId,createTodoDTO)
    }

    @PatchMapping("/todos/{todoId}/checked")
    fun checkTodo(
        @PathVariable todoId: Int,
    ) {
        val userId = authService.getUserId()
        if (!userService.isRightUser(userId, todoId)) throw Exception("잘못된 유저입니다.")
        todoService.checkTodo(todoId)
    }
}