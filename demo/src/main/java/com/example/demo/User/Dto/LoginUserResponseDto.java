package com.example.demo.User.Dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.User.UserVo;

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
public class LoginUserResponseDto {

	private String userId;
	private String password;
	
    public UserVo toVo() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        UserVo userVo = UserVo.builder()
                .userId(this.userId)
                .password(encoder.encode(this.password))
                .build();

        return userVo;
    }
}
