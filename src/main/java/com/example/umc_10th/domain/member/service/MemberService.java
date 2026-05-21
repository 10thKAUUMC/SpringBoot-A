package com.example.umc_10th.domain.member.service;

import com.example.umc_10th.domain.member.converter.MemberConverter;
import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.entity.Food;
import com.example.umc_10th.domain.member.entity.mapping.MemberFood;
import com.example.umc_10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc_10th.domain.member.enums.FoodName;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.member.repository.FoodRepository;
import com.example.umc_10th.domain.member.repository.MemberRepository;
import com.example.umc_10th.domain.member.repository.mapping.MemberFoodRepository;
import com.example.umc_10th.domain.mission.converter.MissionConverter;
import com.example.umc_10th.domain.mission.dto.MissionResDTO;
import com.example.umc_10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc_10th.domain.mission.enums.MissionStatus;
import com.example.umc_10th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto
    ) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    // 홈 화면 데이터 생성
    public MemberResDTO.Home getHomeData(Long memberId) {
        Member member = getMember(memberId);
        MemberResDTO.GetLocation location = MemberConverter.toLocationDTO(member.getLocation());
        MemberResDTO.GetPoint points = MemberConverter.toPointDTO(member.getPoint());
        List<MissionResDTO.GetNearby> missions = getNearbyMissions(member);
        return new MemberResDTO.Home(location, points, missions);
    }

    // 사용자 데이터 조회
    private Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    // 지역 기반 시작 전 상태 미션 목록 조회
    private List<MissionResDTO.GetNearby> getNearbyMissions(Member member) {
        return memberMissionRepository.findHomeMissions(
                        member.getId(),
                        member.getLocation(),
                        MissionStatus.NOT_STARTED
                ).stream()
                .map(MissionConverter::toNearbyMissionDTO)
                .collect(Collectors.toList());
    }

    /**
     * 회원가입 관련 메서드들
     */

    // 이메일 중복 확인
    @Transactional(readOnly = true)
    public void validateEmailDuplicate(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }

    // 회원 저장
    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // 선호 음식 저장
    @Transactional
    public void saveMemberFoods(Member member, List<FoodName> foodNames) {
        for (FoodName foodName : foodNames) {
            // 1. 기존 음식 조회 또는 새로 생성
            Food food = foodRepository.findByName(foodName)
                    .orElseGet(() -> foodRepository.save(
                            Food.builder()
                                    .name(foodName)
                                    .build()
                    ));

            // 2. MemberFood 생성 및 저장
            MemberFood memberFood = MemberFood.builder()
                    .member(member)
                    .food(food)
                    .build();

            memberFoodRepository.save(memberFood);
        }
    }

}
