package com.wskang.trip.service;


import com.wskang.trip.dto.JwtAuthResponseDto;
import com.wskang.trip.dto.LoginDto;
import com.wskang.trip.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JwtAuthResponseDto login(LoginDto loginDto);
}

