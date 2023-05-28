package com.github.vlmaier.engine.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val userDetailsService: UserDetailsService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            securityMatcher("/api/quizzes/**")
            csrf { disable() }
            authorizeHttpRequests {
                authorize(anyRequest, hasRole("USER"))
            }
            httpBasic { }
        }

        return http.build()
    }

    @Bean
    fun authenticationManager(passwordEncoder: BCryptPasswordEncoder): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder)
        val providers = listOf<AuthenticationProvider>(authProvider)
        return ProviderManager(providers)
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}