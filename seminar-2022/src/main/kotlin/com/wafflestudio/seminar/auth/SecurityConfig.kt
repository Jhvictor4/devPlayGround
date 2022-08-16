package com.wafflestudio.seminar.auth

import com.wafflestudio.seminar.auth.filter.JwtAuthFilter
import com.wafflestudio.seminar.auth.filter.SigninAuthenticationFilter
import com.wafflestudio.seminar.auth.service.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletResponse


@Configuration
class SecurityConfig(
    private val tokenService: TokenService,
    private val authenticationConfiguration: AuthenticationConfiguration,
) {
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
    
    @Bean 
    fun authenticationEntryPoint() = AuthenticationEntryPoint { _, response, exc ->
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exc.message)
    }
    
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
    
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            // 1. We'll Only Use JWT Authorization
            .httpBasic().disable()
            .csrf().disable()
            .cors().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 2. Add 401 Exception Handling
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint())
            .and()
            // 3. Allow Only Auth Endpoint
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/v1/users").anonymous()
            .anyRequest().authenticated()
            .and()
            // 4. Add JWT Filter
            .addFilter(SigninAuthenticationFilter(authenticationManager(authenticationConfiguration), tokenService))
            .addFilterBefore(JwtAuthFilter(authenticationManager(authenticationConfiguration), tokenService), UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }
}