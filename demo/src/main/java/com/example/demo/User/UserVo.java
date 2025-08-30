package com.example.demo.User;

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
public class UserVo extends BaseVo {
    private Long id;
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private int role;
}
