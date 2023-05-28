package com.github.vlmaier.engine.entity

import jakarta.persistence.*

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    val id: Long = 0,
    val email: String,
    val password: String,
    val role: String,
)