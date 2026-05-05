package com.example.umc_10th.domain.Term.controller;

import com.example.umc_10th.domain.Term.dto.TermResDTO;
import com.example.umc_10th.domain.Term.exception.code.TermSuccessCode;
import com.example.umc_10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/terms")
public class TermController {

    // 약관 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<TermResDTO.Summary>>> getTerms() {

        List<TermResDTO.Summary> response = List.of(
                TermResDTO.Summary.builder()
                        .termId(1L)
                        .title("이용약관")
                        .required(true)
                        .build(),
                TermResDTO.Summary.builder()
                        .termId(2L)
                        .title("개인정보처리방침")
                        .required(true)
                        .build()
        );

        return ResponseEntity.ok(
                ApiResponse.onSuccess(TermSuccessCode.OK, response)
        );
    }

    // 약관 상세 조회
    @GetMapping("/{termId}")
    public ResponseEntity<ApiResponse<TermResDTO.Detail>> getTerm(
            @PathVariable Long termId
    ) {

        TermResDTO.Detail response = TermResDTO.Detail.builder()
                .termId(termId)
                .title("이용약관")
                .content("약관 상세 내용입니다...")
                .required(true)
                .build();

        return ResponseEntity.ok(
                ApiResponse.onSuccess(TermSuccessCode.OK, response)
        );
    }
}