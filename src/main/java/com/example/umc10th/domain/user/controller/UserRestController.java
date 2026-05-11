package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.mission.domain.MissionStatus;
import com.example.umc10th.domain.mission.domain.UserMission;
import com.example.umc10th.domain.mission.service.UserMissionQueryService;
import com.example.umc10th.domain.user.application.UserService;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.domain.user.converter.UserMissionConverter;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserMissionQueryService userMissionQueryService;

    private final UserService userService;
    //Requires UserRepository and UserService to identify the users' info

    @PostMapping("/missions/progress")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API",
            description = "사용자 ID를 Body로 받아 진행 중인 미션 목록을 조회합니다.")
    public ApiResponse<UserResponseDTO.UserMissionPreViewListDTO> getProgressMissions(
            @RequestBody @Valid UserRequestDTO.MemberMissionQueryDTO request
    ) {

        Page<UserMission> missionPage = userMissionQueryService.getMissions(
                request.memberId(),
                MissionStatus.CHALLENGING,
                request.page()
        );

        return ApiResponse.onSuccess(UserMissionConverter.userMissionPreViewListDTO(missionPage));
    }

    @PostMapping("/join")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(
            @RequestBody @Valid UserRequestDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(userService.join(request));
    }

}
