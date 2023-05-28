package com.github.vlmaier.engine.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateQuizRequest(
    @NotNull
    val title: String,
    @NotNull
    val text: String,
    @Size(min = 2)
    val options: List<String>,
    val answer: List<Int>?,
)
