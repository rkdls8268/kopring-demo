package com.example.nextu.todo.repository

import com.example.nextu.todo.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findOneByEmail(email: String): UserEntity
}