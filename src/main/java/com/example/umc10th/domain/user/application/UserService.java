package com.example.umc10th.domain.user.application;

import com.example.umc10th.domain.user.dao.UserRepository;
import com.example.umc10th.domain.user.domain.User;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service //Take charge of business logic
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private  final UserRepository userRepository;

    @Transactional
    public String join(UserRequestDTO request) {
        //Come up with a logic where it checks for email that is assigned

        //Converts DTO data into Entity
        User newUser = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .build();

        userRepository.save(newUser);

        return "회원가입이 완료되었습니다! ID: " + newUser.getId();
    }
}
