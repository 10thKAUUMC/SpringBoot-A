package com.example.umc_10th.domain.member.service;

import com.example.umc_10th.domain.Term.service.TermService;
import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.dto.MemberResDTO;
import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.global.security.entity.AuthMember;
import com.example.umc_10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final TermService termService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원 가입
    @Transactional
    public MemberResDTO.Signup signup(MemberReqDTO.Signup req) {
        // 1. 약관 검증 - 필수 약관이 모두 동의되었는지 확인
        termService.validateRequiredTerms(req.agreedTerms());

        // 2. 이메일 중복 확인
        memberService.validateEmailDuplicate(req.email());

        // 3. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(req.password());

        // 4. 회원 생성 및 저장
        Member member = Member.builder()
                .name(req.name())
                .email(req.email())
                .password(encodedPassword)
                .gender(req.gender())
                .birth(req.birth())
                .address(req.address())
                .detailAddress(req.detailedAddress())
                .point(0)
                .build();

        Member savedMember = memberService.saveMember(member);

        // 5. 선호 음식 저장
        memberService.saveMemberFoods(savedMember, req.preferredFoods());

        // 6. 약관 동의 기록 저장
        termService.saveMemberTerms(savedMember, req.agreedTerms());

        // 응답 반환
        return MemberResDTO.Signup.builder()
                .memberId(savedMember.getId())
                .name(savedMember.getName())
                .email(savedMember.getEmail())
                .build();
    }

    // 로그인
    @Transactional(readOnly = true)
    public MemberResDTO.Login login(
            MemberReqDTO.Login req
    ) {

        // 이메일로 회원 조회
        Member member = memberService.findByEmail(req.email())
                .orElseThrow(() ->
                        new MemberException(
                                MemberErrorCode.MEMBER_NOT_FOUND
                        ));

        // 비밀번호 검증
        if (!passwordEncoder.matches(
                req.password(),
                member.getPassword()
        )) {
            throw new MemberException(
                    MemberErrorCode.INVALID_PASSWORD
            );
        }

        // 인증 객체 생성
        AuthMember authMember =
                new AuthMember(member);

        // JWT 생성
        String token =
                jwtUtil.createAccessToken(authMember);

        // 응답 반환
        return MemberResDTO.Login.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .token(token)
                .build();
    }
}
