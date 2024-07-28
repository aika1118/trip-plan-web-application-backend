package com.wskang.trip.service.Impl;

import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.entity.Plan;
import com.wskang.trip.entity.User;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.UserRepository;
import com.wskang.trip.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    // username을 통해 userId를 return 함
    @Override
    public Long getUserId(String userName) {
        // userName을 통해 user 엔티티 가져오기 (username은 유니크 키)
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userName:" + userName));

        // 해당 user의 userId를 return
        return user.getUserId();
    }
}