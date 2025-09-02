package com.example.demo.User.Dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.User.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequestDto {
    private String userId;
    private String password;
    private String nickname;
    private String email;
    
    public User toVo() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User.UserBuilder builder = User.builder()
                .userId(this.userId);

        // password 처리
        if (this.password != null && !this.password.trim().isEmpty()) {
            builder.password(encoder.encode(this.password));
        }

        // nickname 처리
        if (this.nickname != null && !this.nickname.trim().isEmpty()) {
            builder.nickname(this.nickname);
        }

        // email 처리
        if (this.email != null && !this.email.trim().isEmpty()) {
            builder.email(this.email);
        }

        return builder.build();
    }

}
