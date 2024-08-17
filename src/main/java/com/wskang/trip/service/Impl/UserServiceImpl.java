package com.wskang.trip.service.Impl;

import com.wskang.trip.entity.User;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.UserRepository;
import com.wskang.trip.service.UserService;
import com.wskang.trip.utils.ValidateUserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * User 관련한 Service 구현
 *
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    // username 또는 email 통해 userId를 return 함
    @Override
    public Long getUserId(String userNameOrEmail) {
        // userName을 통해 user 엔티티 가져오기 (username은 유니크 키)
        User user = userRepository.findByUsernameOrEmail(userNameOrEmail, userNameOrEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found" + userNameOrEmail));

        // 유효성 검증
        ValidateUserUtil.validateUser(user.getUserId());

        // 해당 user의 userId를 return
        return user.getUserId();
    }
}
