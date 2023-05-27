package engine.dto

import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class CreateUserRequest(
    @field:Pattern(regexp = ".+@.+\\..+")
    val email: String,
    @field:Size(min = 5)
    val password: String,
)
