package com.wafflestudio.seminar.auth


//@Configuration
//class SecurityConfig(
//    private val tokenService: TokenService,
//    private val authenticationConfiguration: AuthenticationConfiguration,
//) {
////    @Bean
////    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
////        return authenticationConfiguration.authenticationManager
////    }
//    
////    @Bean 
//    fun authenticationEntryPoint() = AuthenticationEntryPoint { _, response, exc ->
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exc.message)
//    }
//    
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//    
////    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
//        return http
//            // 1. We'll Only Use JWT Authorization
//            .httpBasic().disable()
//            .csrf().disable()
//            .cors().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            // 2. Add 401 Exception Handling
//            .exceptionHandling()
//            .authenticationEntryPoint(authenticationEntryPoint())
//            .and()
//            // 3. Allow Only Auth Endpoint
//            .authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/api/v1/users").anonymous()
//            .anyRequest().authenticated()
//            .and()
//            // 4. Add JWT Filter
////            .addFilter(SigninAuthenticationFilter(authenticationManager(authenticationConfiguration), tokenService))
////            .addFilterBefore(JwtAuthFilter(authenticationManager(authenticationConfiguration), tokenService), UsernamePasswordAuthenticationFilter::class.java)
//            .build()
//    }
//}