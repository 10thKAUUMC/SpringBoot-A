package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.projection.MissionListCursorRow;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionList getMissionList(
            Long memberId,
            boolean isComplete,
            int page,
            Integer size
    ) {
        int pageSize = size == null || size < 1 ? DEFAULT_PAGE_SIZE : size;
        int pageIndex = Math.max(1, page);
        long total = memberMissionRepository.countMissionList(memberId, isComplete);
        int totalPages = pageSize < 1 ? 0 : (int) ((total + pageSize - 1) / pageSize);
        int offset = (pageIndex - 1) * pageSize;

        List<MissionListCursorRow> rows = memberMissionRepository.findMissionListPage(
                memberId,
                isComplete,
                offset,
                pageSize
        );

        List<MissionResDTO.MissionSummary> missions = new ArrayList<>();
        for (MissionListCursorRow row : rows) {
            missions.add(MissionResDTO.MissionSummary.builder()
                    .storeId(row.getStoreId())
                    .storeName(row.getStoreName())
                    .minPrice(row.getMinPrice())
                    .pointPercent(row.getPointPercent())
                    .isComplete(Boolean.TRUE.equals(row.getIsComplete()))
                    .build());
        }

        boolean hasNext = totalPages > 0 && pageIndex < totalPages;

        return MissionResDTO.MissionList.builder()
                .missions(missions)
                .page(pageIndex)
                .size(pageSize)
                .totalElements(total)
                .totalPages(totalPages)
                .hasNext(hasNext)
                .build();
    }

    @Transactional
    public MissionResDTO.MissionComplete completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository
                .findByMember_MemberIdAndMission_Id(memberId, missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        memberMission.markComplete();
        return MissionResDTO.MissionComplete.builder()
                .isComplete(true)
                .message("미션이 성공되었습니다.")
                .build();
    }
}
