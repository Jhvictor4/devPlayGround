package com.wafflestudio.seminar.auth.filter

//class SigninAuthenticationFilter(
//    authenticationManager: AuthenticationManager?,
//    private val tokenService: TokenService,
//) : UsernamePasswordAuthenticationFilter(authenticationManager) {
//    init {
//        setRequiresAuthenticationRequestMatcher(AntPathRequestMatcher("/api/v1/users/signin", "POST"))
//    }
//
//    override fun successfulAuthentication(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        chain: FilterChain,
//        authResult: Authentication
//    ) {
//        response.addHeader("Authorization", tokenService.generateToken(authResult).accessToken)
//        response.status = HttpServletResponse.SC_NO_CONTENT
//    }
//
//    override fun unsuccessfulAuthentication(
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        failed: AuthenticationException
//    ) {
//        super.unsuccessfulAuthentication(request, response, failed);
//        response.status = HttpServletResponse.SC_UNAUTHORIZED
//    }
//
//    // Parse auth request
//    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
//        val parsedRequest = parseRequest(request)
//        val authRequest = UsernamePasswordAuthenticationToken(parsedRequest.email, parsedRequest.password)
//        return authenticationManager.authenticate(authRequest)
//    }
//
//    private fun parseRequest(request: HttpServletRequest): LoginRequest {
//        val reader: BufferedReader = request.reader
//        return jacksonObjectMapper().readValue(reader, LoginRequest::class.java)
//    }
//    
//    data class LoginRequest(
//        val email: String,
//        val password: String,
//    )
//
//}
