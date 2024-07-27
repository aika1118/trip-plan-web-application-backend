package com.wskang.trip.controller;

import com.wskang.trip.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService; // 생성자에서 bean에 등록된 해당 service 의존성 가져옴

    // userName에 대응하는 userId를 return 함
    @GetMapping("{userName}")
    public ResponseEntity<Long> getUserId(@PathVariable("userName") String userName){
        Long userId = userService.getUserId(userName);
        return ResponseEntity.ok(userId);
    }
}
