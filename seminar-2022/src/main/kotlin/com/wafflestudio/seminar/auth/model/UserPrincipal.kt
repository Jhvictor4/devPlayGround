package com.wafflestudio.seminar.auth.model

import com.wafflestudio.seminar.domain.user.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(val user: User): UserDetails {    
    override fun getUsername() = user.email
    override fun getPassword() = user.password
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
    override fun getAuthorities()= user.roles
        .split(",")
        .filter(String::isNotEmpty)
        .map(::SimpleGrantedAuthority)
}