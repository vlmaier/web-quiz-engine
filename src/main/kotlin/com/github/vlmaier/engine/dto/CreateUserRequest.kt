package com.github.vlmaier.engine.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateUserRequest(
    @Pattern(regexp = ".+@.+\\..+")
    val email: String,
    @Size(min = 5)
    val password: String,
)
