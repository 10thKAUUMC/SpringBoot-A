package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.res.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.projection.MissionListCursorRow;
import com.example.umc10th.domain.store.entity.Store;
import com.example.umc10th.domain.store.repository.StoreRepository;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc10th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public MissionResDTO.StoreMissionPage getStoreMissions(
            Long storeId,
            int pageNumber,
            Integer pageSize
    ) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        int size = pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
        int pageIndex = Math.max(1, pageNumber);
        Pageable pageable = PageRequest.of(pageIndex - 1, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Mission> page = missionRepository.findAllByStore_Id(storeId, pageable);

        List<MissionResDTO.GetMission> data = page.getContent().stream()
                .map(MissionConverter::toGetMission)
                .toList();

        int totalPages = page.getTotalPages();
        return MissionResDTO.StoreMissionPage.builder()
                .data(data)
                .pageNumber(pageIndex)
                .pageSize(size)
                .totalElements(page.getTotalElements())
                .totalPages(totalPages)
                .build();
    }

    @Transactional
    public void createStoreMission(Long storeId, MissionReqDTO.CreateMission request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, request);
        missionRepository.save(mission);
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
