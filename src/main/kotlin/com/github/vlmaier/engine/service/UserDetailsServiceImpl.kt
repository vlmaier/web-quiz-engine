package com.github.vlmaier.engine.service

import com.github.vlmaier.engine.model.UserDetailsImpl
import com.github.vlmaier.engine.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userRepository.findByEmail(username).orElseGet { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        return UserDetailsImpl(user)
    }
}