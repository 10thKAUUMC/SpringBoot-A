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
            boolean isComplete,
            int page,
            Integer size
    ) {
        int pageSize = size == null || size < 1 ? DEFAULT_PAGE_SIZE : size;
        int pageIndex = Math.max(1, page);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        long targetMission = memberMissionRepository.countByMemberAndLocationName(memberId, address);
        long progressMission = memberMissionRepository.countCompletedByMemberAndLocationName(memberId, address);
        int progressPoint = memberMissionRepository.sumMissionPointsCompletedByMemberAndLocationName(memberId, address);

        long total = memberMissionRepository.countHomeMissions(memberId, address, isComplete);
        int totalPages = pageSize < 1 ? 0 : (int) ((total + pageSize - 1) / pageSize);
        int offset = (pageIndex - 1) * pageSize;

        List<HomeMissionCursorRow> rows = memberMissionRepository.findHomeMissionsPage(
                memberId,
                address,
                isComplete,
                offset,
                pageSize
        );

        List<HomeResDTO.MissionSummary> missions = new ArrayList<>();
        for (HomeMissionCursorRow row : rows) {
            missions.add(HomeResDTO.MissionSummary.builder()
                    .storeId(row.getStoreId())
                    .storeName(row.getStoreName())
                    .storeCategory(row.getStoreCategory())
                    .dDay(toRemainingDays(row.getDDay()))
                    .minPrice(row.getMinPrice())
                    .accumulatePoint(row.getPoint())
                    .build());
        }

        boolean hasNext = totalPages > 0 && pageIndex < totalPages;

        return HomeResDTO.MyMissions.builder()
                .address(address)
                .myPoint(member.getPoint())
                .progressMission((int) progressMission)
                .targetMission((int) targetMission)
                .progressPoint(progressPoint)
                .missions(missions)
                .page(pageIndex)
                .size(pageSize)
                .totalElements(total)
                .totalPages(totalPages)
                .hasNext(hasNext)
                .build();
    }

    private static int toRemainingDays(LocalDate deadline) {
        if (deadline == null) {
            return 0;
        }
        long days = ChronoUnit.DAYS.between(LocalDate.now(), deadline);
        return (int) Math.max(0, days);
    }
}
