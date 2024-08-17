package com.wskang.trip.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter
 *
 * HTTP 요청을 가로채어 JWT 를 추출하고, 이 토큰의 유효성을 검증하는 class
 *
 */

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    // 필터 : 웹 애플리케이션에서 요청을 가로채어서 처리하는 기능을 담당하는 컴포넌트
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Get JWT token from HTTP request (token is in header)
        String token = getTokenFromRequest(request);

        // Validate Token
        // 유효성 검증 실패 시 validateToken() 내에서 에러에 대한 예외사항 처리 진행
        if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
            // get username from token
            String username = jwtTokenProvider.getUsername(token);

            // CustomUserDetailsService에서 override한 loadUserByUsername을 통해 사용자의 인증 정보를 로드
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 사용자의 인증 정보를 나타내는 객체 생성
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null, // JWT를 사용한 인증 후에는 더 이상 비밀번호와 같은 자격 증명이 필요하지 않기 때문에 null로 설정
                    userDetails.getAuthorities()
            );

            // Spring Security에서 사용자의 인증 요청에 대한 추가 정보를 수집하고 기록
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Spring Security 설정
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // 다음 필터로 요청을 전달
        // 최종적으로는 컨트롤러가 요청을 받아 비즈니스 로직 실행 후 응답을 클라이언트에게 반환
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request){ // request : 클라이언트의 request
        String bearerToken = request.getHeader("Authorization");
        // HTTP request headers 의 Authorization Key 형식 = "Bearer {JWT}"

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        return null;
    }
}

