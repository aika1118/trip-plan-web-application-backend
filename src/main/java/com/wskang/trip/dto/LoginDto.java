package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * LoginDto
 *
 * 로그인 요청을 처리하기 위한 DTO 정의
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String usernameOrEmail; // username 또는 Email로 유저 인증 진행
    private String password;
}

