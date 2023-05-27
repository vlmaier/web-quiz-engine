package engine.model

import engine.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(user: User) : UserDetails {

    private val username: String
    private val password: String
    private val rolesAndAuthorities: List<GrantedAuthority>

    init {
        username = user.email
        password = user.password
        rolesAndAuthorities = listOf<GrantedAuthority>(SimpleGrantedAuthority(user.role))
    }

    override fun getAuthorities() = rolesAndAuthorities

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}