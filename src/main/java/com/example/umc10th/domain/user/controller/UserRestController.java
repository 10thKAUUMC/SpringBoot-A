package com.example.umc10th.domain.user.controller;


import com.example.umc10th.domain.mission.domain.MissionStatus;
import com.example.umc10th.domain.mission.service.UserMissionQueryService;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserMissionQueryService userMissionQueryService;
    // 유저 확인을 위해 UserRepository나 UserService도 필요할 수 있습니다.

    @GetMapping("/me/missions")
    @Operation(summary = "나의 미션 목록 조회 API", description = "진행 중이거나 완료된 미션 목록을 조회하는 API입니다. query string으로 page와 status를 주세요.")
    public ApiResponse<UserResponseDTO.UserMissionPreViewListDTO> getMyMissionList(
            @RequestParam(name = "status") MissionStatus status,
            @RequestParam(name = "page") Integer page
    ) {
        // In reality, you need authorization to access User object,
        // For the test, we create temporary User object or find the ID from Service.
        // Page<UserMission> missionPage = userMissionQueryService.getMyMissionList(user, status, page);

        // return ApiResponse.onSuccess(UserMissionConverter.userMissionPreViewListDTO(missionPage));
        return null; //Gonna keep it as null for now;;
    }

}
