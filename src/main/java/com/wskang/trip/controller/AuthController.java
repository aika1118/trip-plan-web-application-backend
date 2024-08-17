package com.wskang.trip.controller;

import com.wskang.trip.dto.JwtAuthResponseDto;
import com.wskang.trip.dto.LoginDto;
import com.wskang.trip.dto.RegisterDto;
import com.wskang.trip.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController
 *
 * 회원가입, 로그인 관련된 REST API 요청을 처리하는 Controller
 *
 */

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Login REST API
    // JwtAuthResponseDto 에 JWT 토큰을 포함하여 반환
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody LoginDto loginDto){
        JwtAuthResponseDto jwtAuthResponseDto = authService.login(loginDto);

        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.OK);
    }

}

