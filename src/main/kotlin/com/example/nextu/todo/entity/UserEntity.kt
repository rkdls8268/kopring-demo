package com.example.nextu.todo.entity

import javax.persistence.*

@Entity
@Table
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val email: String,
    val name: String
)