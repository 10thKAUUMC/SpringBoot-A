package com.example.umc10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.example.umc10th.domain.user.dao.UserRepository;
import com.example.umc10th.domain.user.domain.User;
import org.springframework.boot.CommandLineRunner;

import java.sql.SQLOutput;

@EnableJpaAuditing //This annotation automatically updates the date when it's added, instead of adding now() individually
@SpringBootApplication
public class Umc10thApplication {

    public static void main(String[] args) {
        SpringApplication.run(Umc10thApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(UserRepository userRepository){
        return args -> {
            User user = User.builder()
                    .nickname("테스트 유저")
                    .email("test@test.com")
                    .build();
            userRepository.save(user);
            System.out.println("✅✅✅체 크  유저 저장 완료!");
        };

    }

}
