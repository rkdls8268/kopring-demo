package com.example.nextu.todo.repository

import com.example.nextu.todo.entity.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<TodoEntity, Int>