package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.res.HomeResDTO;
import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.domain.home.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "홈", description = "지역(주소) 기준 미션·진행 현황 요약")
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @Operation(
            summary = "홈 — 내 미션 목록",
            description = """
                    지정한 **지역명(`address`)**에 해당하는 가게의 미션 중, 회원에게 배정된 미션을 \
                    **오프셋 페이지**로 조회합니다. 응답에 진행 미션 수·포인트 요약 등이 포함됩니다."""
    )
    @GetMapping("/my-missions")
    public ApiResponse<HomeResDTO.MyMissions> getMyMissions(
            @Parameter(description = "true면 완료된 미션만", example = "false")
            @RequestParam(name = "is_complete", required = false, defaultValue = "false") boolean isComplete,
            @Parameter(description = "회원 PK", example = "1")
            @RequestParam(name = "member_id", required = false, defaultValue = "1") Long memberId,
            @Parameter(description = "`location.name`과 매칭되는 지역명(예: 안암동)", example = "안암동")
            @RequestParam(name = "address", required = false, defaultValue = "안암동") String address,
            @Parameter(description = "페이지 번호(1부터)", example = "1")
            @RequestParam(required = false, defaultValue = "1") int page,
            @Parameter(description = "페이지 크기(미지정 시 서버 기본값)")
            @RequestParam(required = false) Integer size
    ) {
        HomeResDTO.MyMissions result = homeService.getMyMissions(
                memberId,
                address,
                isComplete,
                page,
                size
        );
        BaseSuccessCode code = HomeSuccessCode.HOME_MISSION_LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }
}
