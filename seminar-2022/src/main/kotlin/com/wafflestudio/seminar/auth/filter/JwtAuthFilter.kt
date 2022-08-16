package com.wafflestudio.seminar.auth.filter

//class JwtAuthFilter(
//    authenticationManager: AuthenticationManager,
//    private val tokenService: TokenService
//) : BasicAuthenticationFilter(authenticationManager) {
//
//    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
//        val token = request.getHeader("Authorization")
//        if (token != null && tokenService.verifyToken(token)) {
//            SecurityContextHolder.getContext().authentication = tokenService.getCurrentAuth(token)
//        }
//        
//        chain.doFilter(request, response)
//    }
//}