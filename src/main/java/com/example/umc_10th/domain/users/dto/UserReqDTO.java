package com.example.umc_10th.domain.users.dto;

public class UserReqDTO {

    public record JoinDTO(
            String userName,
            String phoneNumber,
            String userId,
            String userPassword
    ){}
}
