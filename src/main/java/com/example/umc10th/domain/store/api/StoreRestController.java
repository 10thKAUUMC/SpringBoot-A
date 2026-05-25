package com.example.umc10th.domain.store.api;

import com.example.umc10th.domain.store.dto.StoreResponseDTO;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/stores")
public class StoreRestController {

    @GetMapping("/home")
    @Operation(summary = "홈 화면 조회 API", description = "추천 가게 목록을 조회합니다.")
    public ApiResponse<StoreResponseDTO.HomeViewListDTO> getHomeView() {
        // Since there is no actual service it will return a fake data or empty list
        StoreResponseDTO.HomeViewListDTO result = StoreResponseDTO.HomeViewListDTO.builder()
                .storeList(new ArrayList<>())
                .build();

        return ApiResponse.onSuccess(result);
    }
}