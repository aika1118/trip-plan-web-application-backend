package com.wskang.trip.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JwtTokenProvider
 *
 * JWT 생성 및 검증을 수행하는 class
 *
 */

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}") //application.properties에서 값 가져오기
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    // Generate JWT token
    public String generateToken(Authentication authentication){
        String usernameOrEmail = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .subject(usernameOrEmail)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    // secret 키 디코딩 (서명 생성에서 비밀 키는 바이트 배열 형태로 사용해야하기 때문에 진행)
    private SecretKey key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // Get username from JWT token
    public String getUsername(String token){
        Claims claims = Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String username = claims.getSubject();

        return username;
    }

    // Validate JWT Token
    public boolean validateToken(String token){

        Jwts.parser()
                .verifyWith(key())
                .build()
                .parse(token); // 토큰 파싱 후 서명이 올바른지, 유효 기간이 지나지 않았는지 등을 확인

        // validate에 실패 시 오류 throw

        return true;
    }

}
