package com.example.umc_10th.domain.member.exception;

import com.example.umc_10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_10th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {super(errorCode);}
}
