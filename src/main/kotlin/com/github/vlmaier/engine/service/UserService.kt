package com.github.vlmaier.engine.service

import com.github.vlmaier.engine.dto.CreateUserRequest
import com.github.vlmaier.engine.entity.User
import com.github.vlmaier.engine.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
) {
    fun registerUser(body: CreateUserRequest) {
        if (userRepository.findByEmail(body.email).isPresent) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
        userRepository.save(
            User(
                email = body.email,
                password = encoder.encode(body.password),
                role = "ROLE_USER",
            )
        )
    }
}
