package com.wafflestudio.seminar.auth.service

import com.wafflestudio.seminar.auth.model.Token
import com.wafflestudio.seminar.auth.model.UserPrincipal
import com.wafflestudio.seminar.domain.user.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenService(
    private val userRepository: UserRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)
    
    val tokenPrefix = "Bearer "
    val headerString = "Authentication"

    // TODO config
    private val jwtSecretKey: String = "WAFFLESTUDIO"

    // TODO config
    private val jwtExpirationInMs: Long = 10000000

    // Generate jwt token with prefix
    fun generateToken(authentication: Authentication): Token {
        val userPrincipal = authentication.principal as UserPrincipal
        val claims: MutableMap<String, Any> = hashMapOf("email" to userPrincipal.user.email)
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)
        val resultToken = tokenPrefix + Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS256, jwtSecretKey)
            .compact()
        // TODO RefreshToken
        return Token(resultToken, resultToken)
    }
    
    fun verifyToken(authToken: String): Boolean {
        if(authToken.isEmpty()){
            log.error("Token is not provided")
            return false
        }
        if (!authToken.startsWith(tokenPrefix)) {
            log.error("Token not match type Bearer")
            return false
        }
        
        try {
            parse(authToken)
            return true
        } catch (ex: SignatureException) {
            log.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            log.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            log.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            log.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            log.error("JWT claims string is empty.")
        }
        return false
    }
    
    private fun parse(authToken: String): Jws<Claims> {
        val prefixRemoved =  authToken.replace(tokenPrefix, "").trim { it <= ' ' }
        return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(prefixRemoved)
    }
    
    fun getCurrentAuth(authToken: String): Authentication {
        val email = parse(authToken).body["email"].toString()
        val member = userRepository.findByEmail(email)
            ?.let(::UserPrincipal)
            ?: throw UsernameNotFoundException("${email}에 해당하는 계정이 없습니다.")
        return UsernamePasswordAuthenticationToken(member.username, member.password, member.authorities)
    }
}

