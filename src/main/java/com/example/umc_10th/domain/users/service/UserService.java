package com.example.umc_10th.domain.users.service;

import com.example.umc_10th.domain.users.dto.UserReqDTO;
import com.example.umc_10th.domain.users.dto.UserResDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserResDTO.JoinResultDTO join(UserReqDTO.JoinDTO request) {
        return UserResDTO.JoinResultDTO.builder()
                .id(1L)
                .userId(request.userId())
                .userName(request.userName())
                .build();
    }
}
