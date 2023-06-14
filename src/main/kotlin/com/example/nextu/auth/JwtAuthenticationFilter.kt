package com.example.nextu.auth

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

// JwtAuthenticationFilter: 헤더에서 JWT 를 받아와 유효한 토큰인지 확인하고, 유효할 경우 유저 정보를 SecurityContextHolder 에 저장
class JwtAuthenticationFilter(
    private val tokenProvider: TokenProvider
): GenericFilterBean() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        // 헤더에서 JWT 를 받아옴. Bearer 로 시작하므로 앞부분은 날려줘야 함
        val token: String? = resolveToken(request as HttpServletRequest)?.let { t -> t.split(" ")[1].trim() }
        if (token != null && tokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아온다.
            val authentication = tokenProvider.getAuthentication(token)
            // securityContext 에 Authentication 객체 저장
            SecurityContextHolder.getContext().authentication = authentication
        }
        // 아래 메소드가 없다면 SecurityConfig 내에서 설정한 옵션들이 실행되지 않고 동작하므로 꼭 작성할 것!
        chain?.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader("Authorization")
    }
}