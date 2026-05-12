package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원 미션", description = "특정 회원의 가게 미션(담은 미션) 목록 조회 및 완료 처리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @Operation(
            summary = "내 미션 목록 조회",
            description = """
                    회원이 참여 중인 `member_mission`을 가게 정보와 함께 **오프셋 페이지**로 조회합니다. \
                    `is_complete`로 미완료/완료만 필터링할 수 있습니다."""
    )
    @GetMapping("/mission-list")
    public ApiResponse<MissionResDTO.MissionList> getMissionList(
            @Parameter(description = "회원 PK", required = true, example = "1")
            @RequestParam(name = "member_id") Long memberId,
            @Parameter(description = "true면 완료된 미션만, false면 진행 중만", example = "false")
            @RequestParam(name = "is_complete", defaultValue = "false") boolean isComplete,
            @Parameter(description = "페이지 번호(1부터)", example = "1")
            @RequestParam(required = false, defaultValue = "1") int page,
            @Parameter(description = "페이지 크기(미지정 시 서버 기본값)")
            @RequestParam(required = false) Integer size
    ) {
        MissionResDTO.MissionList result = missionService.getMissionList(
                memberId,
                isComplete,
                page,
                size
        );
        BaseSuccessCode code = MissionSuccessCode.MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }

    @Operation(
            summary = "미션 완료 처리",
            description = "해당 회원의 특정 미션(`mission_id`)을 완료 상태로 변경합니다."
    )
    @PatchMapping("/{missionId}/complete")
    public ApiResponse<MissionResDTO.MissionComplete> completeMission(
            @Parameter(description = "완료할 미션 PK", required = true, example = "10")
            @PathVariable Long missionId,
            @Parameter(description = "회원 PK", example = "1")
            @RequestParam(name = "member_id", required = false, defaultValue = "1") Long memberId
    ) {
        MissionResDTO.MissionComplete result = missionService.completeMission(memberId, missionId);
        BaseSuccessCode code = MissionSuccessCode.MISSION_COMPLETE_OK;
        return ApiResponse.onSuccess(code, result);
    }
}
