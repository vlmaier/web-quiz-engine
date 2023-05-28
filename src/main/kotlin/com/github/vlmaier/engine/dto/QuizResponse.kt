package com.github.vlmaier.engine.dto

data class QuizResponse(
    val id: Long,
    val title: String?,
    val text: String?,
    val options: List<String>?,
)
