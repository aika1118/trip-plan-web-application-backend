package com.wskang.trip.service.Impl;


import com.wskang.trip.dto.JwtAuthResponseDto;
import com.wskang.trip.dto.LoginDto;
import com.wskang.trip.dto.RegisterDto;
import com.wskang.trip.entity.Role;
import com.wskang.trip.entity.User;
import com.wskang.trip.exception.BadRequestException;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.RoleRepository;
import com.wskang.trip.repository.UserRepository;
import com.wskang.trip.security.JwtTokenProvider;
import com.wskang.trip.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository; // 의존성 주입 (객체 생성시 @bean으로 등록된 spring bean 참고하여 자동으로 주입됨)
    private RoleRepository roleRepository; // 의존성 주입 (객체 생성시 @bean으로 등록된 spring bean 참고하여 자동으로 주입됨)
    private PasswordEncoder passwordEncoder; // 의존성 주입 (객체 생성시 @bean으로 등록된 spring bean 참고하여 자동으로 주입됨) made in SpringSecurityConfig
    private AuthenticationManager authenticationManager; // 의존성 주입 (객체 생성시 @bean으로 등록된 spring bean 참고하여 자동으로 주입됨) made in SpringSecurityConfig
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {

        // check username is already exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())){
                throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username is already in DB !",
                        "DUPLICATE_USERNAME");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email is already in DB !",
                    "DUPLICATE_EMAIL");
        }

        // username, email, password 검증 로직 추가 (클라이언트에도 검증로직 있지만 안전을 위해 서버에도 추가)

        // username :  '@'가 들어가 있으면 안됨
        // email : '@'가 반드시 포함되어야함

        if (registerDto.getUsername().contains("@"))
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username cannot contain a '@'",
                    "INVALID_USERNAME");
        if (!registerDto.getEmail().contains("@"))
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email should contain a '@'",
                    "INVALID_EMAIL");



        // registerDto 정보 기반하여 User 엔티티에 저장
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword())); // 비밀번호는 암호화 함수로 인코딩하여 저장

        // register 시도시 기본적으로 ROLE_USER 역할 부여
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new ResourceNotFoundException("Role name does not exists!"));
        roles.add(userRole);

        user.setRoles(roles);

        // 요청받은 registerDto 정보를 User 엔티티에 담아 DB에 저장
        userRepository.save(user);

        return "User Registered Successfully!";
    }

    @Override
    public JwtAuthResponseDto login(LoginDto loginDto) {

        // 사용자가 입력한 로그인 정보를 사용하여 CustomUserDetailsService를 통해 인증을 수행 (실패시 예외를 던짐)
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        // SecurityContextHolder : 애플리케이션 전역에서 현재 사용자의 보안 정보를 유지하고 접근할 수 있는 중앙 저장소 역할
        // 현재 스레드의 SecurityContext에 인증 객체를 저장
        // 이를 통해 애플리케이션의 다른 부분에서도 인증 정보를 사용할 수 있게 됨
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(authentication);

        // 로그인 요청한 User 엔티티 가져오기
        User loggedInUser = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with UsernameOrEmail:" + loginDto.getUsernameOrEmail()));

        // 로그인 요청한 User의 role 가져오기
        Role userRole = loggedInUser.getRoles().stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User does not hava a role:" + loginDto.getUsernameOrEmail()));

        String role = userRole.getRoleName();

        // 로그인 요청한 User에 대해 JWT 정보를 DTO에 저장
        JwtAuthResponseDto jwtAuthResponseDto = new JwtAuthResponseDto();
        jwtAuthResponseDto.setRole(role);
        jwtAuthResponseDto.setAccessToken(token);

        return jwtAuthResponseDto;
    }
}

