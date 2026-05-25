package com.example.umc10th.global.apiPayload.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GeneralErrorCode implements BaseErrorCode {

    //Success
    _OK(HttpStatus.OK, "COMMON200", "Success"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400_1", "Wrong Request"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401_1", "Require Authentication"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403_1", "Forbidden Access"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "Resources Not Found"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "Error Occurred in the Server"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "Cannot find the User");


    private final HttpStatus status;
    private final String code;
    private final String message;

    GeneralErrorCode(HttpStatus httpStatus, String code, String message){
        this.status = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public ErrorReasonDTO getReason(){
        return ErrorReasonDTO.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus(){
        return ErrorReasonDTO.builder()
                .httpStatus(status)
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }
}
