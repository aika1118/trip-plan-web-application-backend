package com.wskang.trip.service;

import com.wskang.trip.dto.JwtAuthResponseDto;
import com.wskang.trip.dto.LoginDto;
import com.wskang.trip.dto.RegisterDto;

/**
 * AuthService
 *
 * 회원가입 로그인 Service 구현을 위한 interface 정의
 *
 */

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponseDto login(LoginDto loginDto);
}

