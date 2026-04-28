package com.example.umc10th.domain.user.dto;

import lombok.Getter;

public class UserRequestDTO {

    @Getter
    public static class JoinDTO {
        String nickname;
        String email;
        String password;
        String phone;
        String birth;
        String gender;
        String detail_address;
    }


    @Getter
    public static class PostReviewDTO {
        Long store_id;
        Integer rating;
        String content;
    }


}
