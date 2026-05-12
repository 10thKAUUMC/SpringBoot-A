package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.req.MemberReqDTO;
import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.exeption.MemberException;
import com.example.umc10th.domain.member.exeption.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    // 마이페이지
    public MemberResDTO.GetInfo getInfo(
            MemberReqDTO.GetInfo dto
    ) {
        Long memberId = dto.id();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.Signup signup(MemberReqDTO.Signup request) {
        Gender gender = Gender.valueOf(request.gender().trim().toUpperCase());
        Member member = Member.builder()
                .name(request.userName())
                .gender(gender)
                .birth(request.birthday())
                .address(request.address())
                .detailAddress(request.detailAddress())
                .email(request.email())
                .phoneNumber(request.phone())
                .point(0)
                .build();
        memberRepository.save(member);

        if (request.category() != null) {
            for (String categoryName : request.category()) {
                Food food = foodRepository.findByName(categoryName)
                        .orElseGet(() -> foodRepository.save(
                                Food.builder().name(categoryName).build()
                        ));
                memberFoodRepository.save(MemberFood.builder()
                        .member(member)
                        .food(food)
                        .build());
            }
        }

        return MemberResDTO.Signup.builder()
                .userName(request.userName())
                .gender(request.gender())
                .birthday(request.birthday())
                .address(request.address())
                .detailAddress(request.detailAddress())
                .email(request.email())
                .phone(request.phone())
                .category(request.category())
                .message("회원가입에 성공했습니다.")
                .build();
    }
}
