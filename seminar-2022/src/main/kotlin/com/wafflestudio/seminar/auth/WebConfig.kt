package com.wafflestudio.seminar.auth

import com.wafflestudio.seminar.auth.service.TokenService
import org.springframework.context.annotation.Configuration
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.servlet.http.HttpServletRequest


@Configuration
class WebConfig(
    private val tokenService: TokenService,
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(UserContextResolver(tokenService))
    }
}

class UserContextResolver(
    private val tokenService: TokenService,
): HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.getParameterAnnotation(UserContext::class.java) != null
                && Long::class.java.isAssignableFrom(parameter.parameterType)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Long {
        val authToken = (webRequest.nativeRequest as HttpServletRequest).getHeader("Authorization") ?: throw AuthException("TOKEN IS NULL!")
        if (!tokenService.verifyToken(authToken)) {
            throw AuthException("FAILED TO RETRIEVE USER ID.")
        }

        return tokenService.getCurrentUserId(authToken) 
    }
}

annotation class UserContext

class AuthException(val msg: String = ""): RuntimeException(msg)
