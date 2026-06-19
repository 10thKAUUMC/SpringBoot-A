package com.example.umc_10th.domain.users.service;

import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResDTO.JoinResultDTO join(UserReqDTO.JoinDTO request) {

        if (userRepository.existsByLoginId(request.loginId())) {
            throw new RuntimeException("이미 존재하는 로그인 ID입니다.");
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .userName(request.userName())
                .phoneNumber(request.phoneNumber())
                .loginId(request.loginId())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .gender(request.gender())
                .birth(request.birth())
                .address(request.address())
                .totalPoint(0)
                .isAuthenticated(false)
                .build();

        User savedUser = userRepository.save(user);

        return UserResDTO.JoinResultDTO.builder()
                .id(savedUser.getId())
                .loginId(savedUser.getLoginId())
                .userName(savedUser.getUserName())
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
