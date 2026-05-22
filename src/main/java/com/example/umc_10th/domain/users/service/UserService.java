package com.example.umc_10th.domain.users.service;

import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import com.example.umc_10th.domain.users.entity.User;
import com.example.umc_10th.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.umc_10th.global.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

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

    public UserResDTO.MyPageDTO getMyPage(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));

        return UserResDTO.MyPageDTO.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isAuthenticated(user.getIsAuthenticated())
                .totalPoint(user.getTotalPoint())
                .build();
    }

    public UserResDTO.LoginResultDTO login(UserReqDTO.LoginDTO request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getEmail());

        return UserResDTO.LoginResultDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .accessToken(accessToken)
                .build();
    }
}
