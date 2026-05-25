package com.example.umc10th.global.apiPayload.exception;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.ErrorReasonDTO;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<Object> onThrowException(GeneralException exception, WebRequest request){
        BaseErrorCode errorCode = exception.getErrorCode();
        return handleExceptionInternal(exception, errorCode, null, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        //1. From errors calls out initial error message
        FieldError fieldError = e.getBindingResult().getFieldErrors().getFirst();

        String errorMessage = fieldError.getDefaultMessage();

        //2. If status is null, it returns 400(Bad Request)
        HttpStatusCode responseStatus = (status != null) ? status : HttpStatusCode.valueOf(400);


        return ResponseEntity
                .status(responseStatus)
                .body(ApiResponse.onFailure(
                        GeneralErrorCode.BAD_REQUEST.getCode(),
                        errorMessage,
                        null
                ));
    }
        private ResponseEntity<Object> handleExceptionInternal(Exception e, BaseErrorCode errorCode, HttpHeaders headers, WebRequest request) {

            ErrorReasonDTO reason = errorCode.getReasonHttpStatus();

            ApiResponse<Object> body = ApiResponse.onFailure(
                    reason.getCode(),
                    reason.getMessage(),
                    null);

            return super.handleExceptionInternal(e, body, headers, reason.getHttpStatus(), request);
        }

}

