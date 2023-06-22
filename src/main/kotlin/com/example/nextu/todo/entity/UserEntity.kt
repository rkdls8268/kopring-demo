package com.example.nextu.todo.entity

import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToMany(mappedBy = "user")
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
): BaseTime()