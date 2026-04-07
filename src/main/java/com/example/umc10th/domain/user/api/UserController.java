package com.example.umc10th.domain.user.api;

import com.example.umc10th.domain.user.application.UserService;
import com.example.umc10th.domain.user.dto.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "User API",  description = "사용자 관련 인터페이스")
@RestController //Declares that this class interacts with JSON data
@RequiredArgsConstructor //Sets common address
@RequestMapping("/api/users") //This automatically converts into UserRequestDTO class from JSON data in HTTP Body
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    @Operation(summary = "회원가입 API", description = "새로운 유저를 등록합니다.")
    public String join(@RequestBody UserRequestDTO request){
        //Sends DTO to the Service for WORK :)
        return userService.join(request);

    }

}
