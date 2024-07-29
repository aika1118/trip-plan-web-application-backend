package com.wskang.trip.controller;

import com.wskang.trip.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService; // 생성자에서 bean에 등록된 해당 service 의존성 가져옴

    // username 또는 email에 대응하는 userId를 return 함
    @GetMapping("{userName}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Long> getUserId(@PathVariable("userName") String userName){
        Long userId = userService.getUserId(userName);
        return ResponseEntity.ok(userId);
    }
}
