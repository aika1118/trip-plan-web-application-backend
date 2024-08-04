package com.wskang.trip.utils;

import com.wskang.trip.entity.Plan;
import com.wskang.trip.entity.User;
import com.wskang.trip.exception.BadRequestException;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.PlanRepository;
import com.wskang.trip.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidatePlanUtil {

    private static UserRepository userRepository; // static은 객체에 속하지 않기 때문에 생성자로 의존성 주입 불가

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        ValidatePlanUtil.userRepository = userRepository;
    }

    // 클라이언트에서 보낸 DTO가 현재 인증된 user가 소유하고 있는 것이 맞을지 체크
    public static void validatePlan(Long userId) {
        if (userId == null)
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Bad Request with no id");

        // 현재 인증된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();

        // 클라이언트에서 보낸 DTO를 소유한 user 추출
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));


        // 현재 인증된 사용자와 클라이언트에서 보낸 Dto를 소유한 user 정보가 일치하는지 체크
        if (user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail))
            return;

        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Authorization failed!");
    }
}