package com.wskang.trip.config;

import com.wskang.trip.security.JwtAuthenticationEntryPoint;
import com.wskang.trip.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity // 메소드 레벨 security 적용
@AllArgsConstructor
public class SpringSecurityConfig {


    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;

    // 비밀번호 인코딩에 사용할 spring bean 등록
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // 단방향 해시 함수로 비밀번호 안전하게 보관
    }

    // AuthService에서 로그인 구현에 사용할 spring bean 등록

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // SecurityFilterChain = interface
    // http.build() 반환형 = defaultSecurityFilterChain = interface 구현 클래스
    // Spring 애플리케이션이 실행될 때 자동으로 구성
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // RESTful API에서 JWT를 사용하면 무상태(stateless) 통신을 하므로 CSRF 보호가 불필요
        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/api/auth/**").permitAll(); // 계정 생성 또는 최초 로그인 시도시에는 JWT가 없어서 모두 허가
            authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // 클라이언트의 preflight request는 모두 허가
            authorize.anyRequest().authenticated(); // 이외 모든 요청에 대해 JWT 검증 적용
        }).addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class); // HTTP 요청을 가로채 해당 필터에서 JWT 인증을 먼저 진행할 수 있도록 설정

        // 인증되지 않은 사용자가 리소스 액세스 하려고할 때 예외처리
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }
}
