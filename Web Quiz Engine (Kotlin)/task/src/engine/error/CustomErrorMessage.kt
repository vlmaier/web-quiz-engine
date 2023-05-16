package engine.error

import java.time.LocalDateTime

data class CustomErrorMessage(
    val statusCode: Int,
    val timestamp: LocalDateTime,
    val message: String?,
    val description: String
)