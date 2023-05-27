package engine.dto

import java.util.Date

data class CompletedQuizResponse(
    val id: Long,
    val completedAt: Date,
)
