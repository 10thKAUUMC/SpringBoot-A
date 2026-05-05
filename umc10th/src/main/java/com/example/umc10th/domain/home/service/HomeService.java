package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.dto.res.HomeResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exeption.MemberException;
import com.example.umc10th.domain.member.exeption.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.projection.HomeMissionCursorRow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public HomeResDTO.MyMissions getMyMissions(
            Long memberId,
            String address,
            int isComplete,
            String cursor,
            Integer size
    ) {
        int pageSize = size == null || size < 1 ? DEFAULT_PAGE_SIZE : size;
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        long targetMission = memberMissionRepository.countByMemberAndLocationName(memberId, address);
        long progressMission = memberMissionRepository.countCompletedByMemberAndLocationName(memberId, address);
        int progressPoint = memberMissionRepository.sumMissionPointsCompletedByMemberAndLocationName(memberId, address);

        List<HomeMissionCursorRow> raw = memberMissionRepository.findHomeMissionsWithCursor(
                memberId,
                address,
                isComplete,
                emptyToNull(cursor),
                pageSize + 1
        );
        boolean hasNext = raw.size() > pageSize;
        List<HomeMissionCursorRow> slice = hasNext ? raw.subList(0, pageSize) : raw;
        String nextCursor = null;
        if (hasNext && !slice.isEmpty()) {
            nextCursor = slice.get(slice.size() - 1).getCursorValue();
        }

        List<HomeResDTO.MissionSummary> missions = new ArrayList<>();
        for (HomeMissionCursorRow row : slice) {
            missions.add(HomeResDTO.MissionSummary.builder()
                    .storeId(row.getStoreId())
                    .storeName(row.getStoreName())
                    .storeCategory(row.getStoreCategory())
                    .dDay(toRemainingDays(row.getDDay()))
                    .minPrice(row.getMinPrice())
                    .accumulatePoint(row.getPoint())
                    .build());
        }

        return HomeResDTO.MyMissions.builder()
                .address(address)
                .myPoint(member.getPoint())
                .progressMission((int) progressMission)
                .targetMission((int) targetMission)
                .progressPoint(progressPoint)
                .missions(missions)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }

    private static String emptyToNull(String cursor) {
        return (cursor == null || cursor.isBlank()) ? null : cursor;
    }

    private static int toRemainingDays(LocalDate deadline) {
        if (deadline == null) {
            return 0;
        }
        long days = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
        return (int) Math.max(0, days);
    }
}
