package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JwtAuthResponseDto
 *
 * login 후 사용자에게 반환될 DTO 정의
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponseDto {
    private String accessToken;
    private String tokenType = "Bearer"; // 토큰을 소지한 자가 특정 리소스에 접근할 수 있는 권한을 가짐
    private String role;
}

