package com.example.umc10th.global.apiPayload.exception;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.GeneralErrorCode;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request){

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

}
