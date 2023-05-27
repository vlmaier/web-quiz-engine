package engine.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
open class WebSecurityConfig(
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(getEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .mvcMatchers("/api/quizzes/**").hasAnyRole("USER")
            .anyRequest().permitAll() // make remaining endpoints public (including POST /register)
            .and()
            .csrf().disable() // disabling CSRF will allow sending POST request using Postman
            .httpBasic() // enables basic auth.
    }

    @Bean
    open fun getEncoder() = BCryptPasswordEncoder()
}