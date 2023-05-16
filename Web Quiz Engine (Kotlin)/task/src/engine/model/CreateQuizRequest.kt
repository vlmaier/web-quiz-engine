package engine.model

data class CreateQuizRequest(
    val title: String?,
    val text: String?,
    val options: List<String>?,
    val answer: Int?,
)
