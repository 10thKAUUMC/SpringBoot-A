package com.example.umc10th.domain.user.api;

import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import com.example.umc10th.domain.user.dto.UserResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@Tag(name = "User API",  description = "사용자 관련 인터페이스")
@RestController //Declares that this class interacts with JSON data
@RequiredArgsConstructor //Sets common address
@RequestMapping("/api/users") //This automatically converts into UserRequestDTO class from JSON data in HTTP Body
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원가입 API", description = "새로운 유저를 등록합니다.")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody UserRequestDTO.JoinDTO request){

        UserResponseDTO.JoinResultDTO result = userService.join(request);

        return ApiResponse.onSuccess(result);

    }

    @PostMapping("/{user_id}/reviews")
    @Operation(summary = "리뷰 작성 API", description = "특정 유저가 가게에 리뷰를 남깁니다.")
    public ApiResponse<UserResponseDTO.PostReviewResultDTO> postReview(
            @PathVariable(name = "user_id") Long userId,
            @RequestBody UserRequestDTO.PostReviewDTO request
    ){
        // Let this thing do its work
        UserResponseDTO.PostReviewResultDTO result = userService.postReview(userId, request);

        return ApiResponse.onSuccess(result);
    }

}
