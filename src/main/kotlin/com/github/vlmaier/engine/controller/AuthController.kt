package com.github.vlmaier.engine.controller

import com.github.vlmaier.engine.dto.CreateUserRequest
import com.github.vlmaier.engine.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/register")
class AuthController(
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