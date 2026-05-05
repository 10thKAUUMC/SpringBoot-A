package com.example.umc_10th.domain.users.service;

import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResDTO.JoinResultDTO join(UserReqDTO.JoinDTO request) {
        return UserResDTO.JoinResultDTO.builder()
                .id(1L)
                .loginId(request.loginId())
                .userName(request.userName())
                .build();
    }

    public UserResDTO.MyPageDTO getMyPage() {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        return UserResDTO.MyPageDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isAuthenticated(user.getIsAuthenticated())
                .totalPoint(user.getTotalPoint())
                .build();
    }
}
