package engine.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateQuizRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val text: String,
    @field:Size(min = 2)
    val options: List<String>,
    val answer: List<Int>?,
)
