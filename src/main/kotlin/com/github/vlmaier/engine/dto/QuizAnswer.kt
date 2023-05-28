package com.github.vlmaier.engine.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class QuizAnswer(
    @JsonProperty("answer")
    val answer: List<Int>?
)
