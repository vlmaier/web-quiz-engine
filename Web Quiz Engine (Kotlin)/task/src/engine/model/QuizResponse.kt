package engine.model

data class QuizResponse(
    val id: Int,
    val title: String?,
    val text: String?,
    val options: List<String>?,
)
