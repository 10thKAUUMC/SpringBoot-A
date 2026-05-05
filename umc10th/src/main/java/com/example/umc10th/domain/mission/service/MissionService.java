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
            int isComplete,
            String cursor,
            Integer size
    ) {
        int pageSize = size == null || size < 1 ? DEFAULT_PAGE_SIZE : size;

        List<MissionListCursorRow> raw = memberMissionRepository.findMissionListWithCursor(
                memberId,
                isComplete,
                emptyToNull(cursor),
                pageSize + 1
        );
        boolean hasNext = raw.size() > pageSize;
        List<MissionListCursorRow> slice = hasNext ? raw.subList(0, pageSize) : raw;
        String nextCursor = null;
        if (hasNext && !slice.isEmpty()) {
            nextCursor = slice.get(slice.size() - 1).getCursorValue();
        }

        List<MissionResDTO.MissionSummary> missions = new ArrayList<>();
        for (MissionListCursorRow row : slice) {
            missions.add(MissionResDTO.MissionSummary.builder()
                    .storeId(row.getStoreId())
                    .storeName(row.getStoreName())
                    .minPrice(row.getMinPrice())
                    .pointPercent(row.getPointPercent())
                    .isComplete(row.getIsComplete())
                    .build());
        }

        return MissionResDTO.MissionList.builder()
                .missions(missions)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    @Transactional
    public MissionResDTO.MissionComplete completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository
                .findByMember_IdAndMission_Id(memberId, missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        memberMission.markComplete();
        return MissionResDTO.MissionComplete.builder()
                .isComplete(1)
                .message("미션이 성공되었습니다.")
                .build();
    }

    private static String emptyToNull(String cursor) {
        return (cursor == null || cursor.isBlank()) ? null : cursor;
    }
}
