package com.wafflestudio.seminar.auth

import com.wafflestudio.seminar.auth.model.Token
import com.wafflestudio.seminar.auth.service.TokenService
import com.wafflestudio.seminar.domain.user.User
import com.wafflestudio.seminar.domain.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
//import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class SignUpController(
    private val userRepository: UserRepository,
//    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService,
) {
    @PostMapping
    fun signUp(@RequestBody request: AuthRequest) {
        userRepository.save(request.toEntity())
    }
    
    @PostMapping("/signin")
    fun signIn(@RequestBody request: AuthRequest): Token {
        val user = userRepository.findByEmail(request.email)!!
        
        // TODO encrypt
        require(user.password == request.password)
        
        return tokenService.generateTokenByEmail(user.email)
    }
    
    @GetMapping("/me")
    fun getMe(@UserContext userId: Long) = userRepository.findByIdOrNull(userId)
    
    private fun AuthRequest.toEntity(): User {
        return User(email, password)
    }
    
    data class AuthRequest(
        val email: String,
        val password: String,
    )
}