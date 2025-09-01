package com.example.demo.User;

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
public class SessionUser {
    private Long id;
    private String userId;
    private String nickname;
    private String email;
    private int role;
    

    // 임시 생성 메서드 (테스트용)
    public static SessionUser tempUser() {
        return SessionUser.builder()
                .id(1L)
                .userId("tempUser")
                .nickname("임시닉네임")
                .email("temp@example.com")
                .role(1)
                .build();
    }
}
