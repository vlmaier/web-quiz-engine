package engine.controller

import engine.dto.CreateUserRequest
import engine.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/register")
class RegistrationController(
    private val userService: UserService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun createQuiz(
        @RequestBody @Valid body: CreateUserRequest
    ) {

        return userService.registerUser(body)
    }
}