package com.wafflestudio.seminar.auth.service

import com.wafflestudio.seminar.auth.model.UserPrincipal
import com.wafflestudio.seminar.domain.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserPrincipalDetailService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(s: String): UserDetails {
        val user = userRepository.findByEmail(s) ?: throw UsernameNotFoundException("${s}에 해당하는 계정이 없습니다.")
        return UserPrincipal(user)
    }
}