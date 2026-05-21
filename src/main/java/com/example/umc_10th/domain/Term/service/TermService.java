package com.example.umc_10th.domain.Term.service;

import com.example.umc_10th.domain.Term.entity.Term;
import com.example.umc_10th.domain.Term.enums.RequirementType;
import com.example.umc_10th.domain.Term.repository.TermRepository;
import com.example.umc_10th.domain.member.dto.MemberReqDTO;
import com.example.umc_10th.domain.member.entity.Member;
import com.example.umc_10th.domain.member.entity.mapping.MemberTerm;
import com.example.umc_10th.domain.member.exception.MemberException;
import com.example.umc_10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc_10th.domain.member.repository.mapping.MemberTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;
    private final MemberTermRepository memberTermRepository;

    // 필수 약관 검증
    @Transactional(readOnly = true)
    public void validateRequiredTerms(List<MemberReqDTO.TermAgreement> agreedTerms) {
        // 1. DB에서 모든 필수 약관 조회
        List<Term> requiredTerms = termRepository.findByRequired(RequirementType.REQUIRED);

        // 2. 클라이언트가 전달한 약관 ID 중에서 동의한 약관의 ID만 추출
        Set<Long> agreedTermIds = agreedTerms.stream()
                .filter(MemberReqDTO.TermAgreement::agreed)
                .map(MemberReqDTO.TermAgreement::termId)
                .collect(Collectors.toSet());

        // 3. 모든 필수 약관이 동의 목록에 포함되어 있는지 확인
        requiredTerms.forEach(term -> {
            if (!agreedTermIds.contains(term.getId())) {
                throw new MemberException(MemberErrorCode.TERMS_NOT_AGREED);
            }
        });

        // 4. 전달받은 약관 ID들이 실제로 DB에 존재하는지 검증
        agreedTerms.forEach(agreement -> {
            termRepository.findById(agreement.termId())
                    .orElseThrow(() -> new MemberException(MemberErrorCode.TERM_NOT_FOUND));
        });
    }

    // 사용자 약관 동의 기록 저장
    @Transactional
    public void saveMemberTerms(Member member, List<MemberReqDTO.TermAgreement> agreedTerms) {
        for (MemberReqDTO.TermAgreement agreement : agreedTerms) {
            Term term = termRepository.findById(agreement.termId())
                    .orElseThrow(() -> new MemberException(MemberErrorCode.TERM_NOT_FOUND));

            MemberTerm memberTerm = MemberTerm.builder()
                    .member(member)
                    .term(term)
                    .agreed(agreement.agreed())
                    .agreedAt(LocalDateTime.now())
                    .build();

            memberTermRepository.save(memberTerm);
        }
    }
}
