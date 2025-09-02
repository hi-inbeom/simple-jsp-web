package com.example.demo.User.Dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.User.User;

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
public class FindUserRequestDto {
	private String userId;
	private String password;
	
    public User toVo() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        User userVo = User.builder()
                .userId(this.userId)
                .password(encoder.encode(this.password))
                .build();

        return userVo;
    }
}
