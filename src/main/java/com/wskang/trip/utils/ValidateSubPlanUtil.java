package com.wskang.trip.utils;

import com.wskang.trip.entity.DailyPlan;
import com.wskang.trip.entity.Plan;
import com.wskang.trip.entity.User;
import com.wskang.trip.exception.BadRequestException;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.DailyPlanRepository;
import com.wskang.trip.repository.PlanRepository;
import com.wskang.trip.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ValidateSubPlanUtil {
    private static DailyPlanRepository dailyPlanRepository; // static은 객체에 속하지 않기 때문에 생성자로 의존성 주입 불가

    @Autowired
    public void setDailyPlanRepository(DailyPlanRepository dailyPlanRepository) {
        ValidateSubPlanUtil.dailyPlanRepository = dailyPlanRepository;
    }


    // 클라이언트에서 보낸 DTO가 현재 인증된 user가 소유하고 있는 것이 맞을지 체크
    public static void validateDailyPlan(Long dailyId) {
        if (dailyId == null)
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Bad Request with no id",
                    "INVALID_ID");

        // 현재 인증된 사용자 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usernameOrEmail = authentication.getName();

        // 클라이언트에서 보낸 DTO를 소유한 user 추출과정
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyId)
                .orElseThrow(() -> new ResourceNotFoundException("Daily Plan not found with id:" + dailyId));

        User user = dailyPlan.getPlan().getUser();

        // 현재 인증된 사용자와 클라이언트에서 보낸 Dto를 소유한 user 정보가 일치하는지 체크
        if (user.getUsername().equals(usernameOrEmail) || user.getEmail().equals(usernameOrEmail))
            return;

        throw new BadRequestException(HttpStatus.BAD_REQUEST, "Cannot access to other user",
                "BAD_ACCESS");
    }
}
