package com.wafflestudio.seminar.auth

import com.wafflestudio.seminar.domain.user.User
import com.wafflestudio.seminar.domain.user.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class SignUpController(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    @PostMapping
    fun signUp(@RequestBody request: SignUpRequest) {
        userRepository.save(request.toEntity())
    }
    
    private fun SignUpRequest.toEntity(): User {
        return User(email, passwordEncoder.encode(password))
    }
    
    data class SignUpRequest(
        val email: String,
        val password: String,
    )
}