package com.wafflestudio.seminar.auth.filter

import com.wafflestudio.seminar.auth.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthFilter(
    authenticationManager: AuthenticationManager,
    private val tokenService: TokenService
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val token = request.getHeader("Authorization")
        if (token != null && tokenService.verifyToken(token)) {
            SecurityContextHolder.getContext().authentication = tokenService.getCurrentAuth(token)
        }
        
        chain.doFilter(request, response)
    }
}