package com.example.demo.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
public class JoinUserRequestDto {

    @Pattern(regexp = "^[a-z0-9-_]{4,20}$", message = "아이디는 특수문자를 제외한 4~20자리여야 합니다.")
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userId;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문, 숫자, 특수문자")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9-_]{2,10}$", message = "닉네임은 2~10자리여야 합니다.")
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String nickname;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    private String role;
    
    public UserVo toVo() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        UserVo userVo = UserVo.builder()
                .userId(this.userId)
                .password(encoder.encode(this.password))
                .nickname(this.nickname)
                .email(this.email)
                .role(this.role != null ? Integer.parseInt(this.role) : 1) // 기본값 USER
                .build();

        return userVo;
    }
}
