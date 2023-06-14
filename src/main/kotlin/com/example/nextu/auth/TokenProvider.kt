package com.example.nextu.auth

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.stream.Collectors
import javax.crypto.SecretKey

// TokenProvider: JWT 토큰 발급, 인증 정보 조회, 회원 정보 추출 등
@Component
class TokenProvider(
    val userDetailService: CustomUserDetailsService,
    @Value("\${jwt.expiresIn}")
    val expiresIn: Long,
) {
    private val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun createToken(authentication: Authentication): String {
        val authorities: String = authentication.authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","))

        val claims = Jwts.claims().setSubject(authentication.name)
        claims["username"] = authentication.name

        val now = Date.from(Instant.now())
        val time = LocalDateTime.now().plusSeconds(expiresIn)
        val expiryDate = Date.from(time.atZone(ZoneId.systemDefault()).toInstant())

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val username = Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
        val userDetails = userDetailService.loadUserByUsername(username)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    // 토큰 유효성 검사
    fun validateToken(token: String): Boolean {
        try {
            Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
            return true
        } catch (e: MalformedJwtException) {
            throw Exception("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            throw Exception("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            throw Exception("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            throw Exception("JWT 토큰이 잘못되었습니다.")
        }
    }
}