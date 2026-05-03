package com.example.umc_10th.domain.Term.dto;

import lombok.Builder;

public class TermResDTO {
    // 리스트 조회용
    @Builder
    public record Summary(
            Long termId,
            String title,
            Boolean required
    ) {}

    // 상세 조회용
    @Builder
    public record Detail(
            Long termId,
            String title,
            String content,
            Boolean required
    ) {}
}
