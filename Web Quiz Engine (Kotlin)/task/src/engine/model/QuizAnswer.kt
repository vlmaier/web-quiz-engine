package engine.model

import com.fasterxml.jackson.annotation.JsonProperty

data class QuizAnswer(
    @JsonProperty("answer")
    val answer: List<Int>?
)
