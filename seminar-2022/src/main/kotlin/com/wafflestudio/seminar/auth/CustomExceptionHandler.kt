package com.wafflestudio.seminar.auth

//import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(AuthException::class)
    fun catch(e: AuthException): ResponseEntity<String> {
        return ResponseEntity
            .internalServerError()
            .body(e.msg)
    }
}