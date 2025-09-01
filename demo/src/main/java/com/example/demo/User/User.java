package com.example.demo.User;

import java.security.Timestamp;

import com.example.demo.common.BaseVo;

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
public class User extends BaseVo {
    private Long id;			// 기본키
    private String userId;		// 아이디
    private String password;	// 비밀번호
    private String nickname;	// 닉네임
    private String email;		// 이메일
    private int role;			// 유저 권한 0 어드민 1 일반 회원
}
