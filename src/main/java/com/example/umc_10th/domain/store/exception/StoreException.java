package com.example.umc_10th.domain.store.exception;

import com.example.umc_10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc_10th.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) {super(code);}
}
