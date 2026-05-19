package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.req.MemberReqDTO;
import com.example.umc10th.domain.member.dto.res.MemberResDTO;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.Term;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.exeption.MemberException;
import com.example.umc10th.domain.member.exeption.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.member.repository.MemberTermRepository;
import com.example.umc10th.domain.member.repository.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private static final String TERM_AGE = "IS_AGE_OVER_14";
    private static final String TERM_SERVICE = "TERMS_OF_SERVICE";
    private static final String TERM_PRIVACY = "PRIVACY_POLICY";
    private static final String TERM_LOCATION = "LOCATION";
    private static final String TERM_MARKETING = "MARKETING";

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final TermRepository termRepository;
    private final MemberTermRepository memberTermRepository;
    private final PasswordEncoder passwordEncoder;

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
        if (memberRepository.existsByEmail(request.username())) {
            throw new MemberException(MemberErrorCode.DUPLICATE_USERNAME);
        }
        validateRequiredAgreements(request.agreements());

        Gender gender = parseGender(request.profile().gender());
        Member member = Member.builder()
                .name(request.profile().name())
                .gender(gender)
                .birth(request.profile().birthDate())
                .address(request.profile().address())
                .detailAddress(request.profile().detailAddress())
                .email(request.username())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(null)
                .point(0)
                .build();
        Member saved = memberRepository.save(member);

        saveAgreement(saved, TERM_AGE, request.agreements().isAgeOver14());
        saveAgreement(saved, TERM_SERVICE, request.agreements().termsOfService());
        saveAgreement(saved, TERM_PRIVACY, request.agreements().privacyPolicy());
        saveAgreement(saved, TERM_LOCATION, request.agreements().location());
        saveAgreement(saved, TERM_MARKETING, request.agreements().marketing());

        if (request.favoriteFoodCategories() != null) {
            for (String categoryName : request.favoriteFoodCategories()) {
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
                .status(201)
                .message("회원가입이 완료되었습니다.")
                .data(MemberResDTO.SignupData.builder()
                        .userId(saved.getMemberId())
                        .build())
                .build();
    }

    private void validateRequiredAgreements(MemberReqDTO.Agreements agreements) {
        if (!Boolean.TRUE.equals(agreements.isAgeOver14())
                || !Boolean.TRUE.equals(agreements.termsOfService())
                || !Boolean.TRUE.equals(agreements.privacyPolicy())) {
            throw new MemberException(MemberErrorCode.INVALID_AGREEMENT);
        }
    }

    private void saveAgreement(Member member, String termName, Boolean agreed) {
        if (!Boolean.TRUE.equals(agreed)) {
            return;
        }
        Term term = termRepository.findByName(termName)
                .orElseGet(() -> termRepository.save(Term.builder().name(termName).build()));
        memberTermRepository.save(MemberTerm.builder()
                .member(member)
                .term(term)
                .build());
    }

    private Gender parseGender(String rawGender) {
        try {
            return Gender.valueOf(rawGender.trim().toUpperCase());
        } catch (Exception ignored) {
            throw new MemberException(MemberErrorCode.INVALID_GENDER);
        }
    }
}
