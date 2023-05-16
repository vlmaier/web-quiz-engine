package engine.model

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateQuizRequest(
    @NotNull
    val title: String,
    @NotNull
    val text: String,
    @Size(min = 2)
    val options: List<String>,
    val answer: List<Int>?,
)
