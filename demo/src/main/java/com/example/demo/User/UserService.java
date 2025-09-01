package com.example.demo.User;

import org.springframework.stereotype.Service;

import com.example.demo.User.Dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    
    /**
     * 아이디로 유저 조회
     */
    public UserResponseDto findByUserId(String userId) {
        UserVo userVo = userDao.findByUserId(userId);

        // UserVo → UserResponseDto 변환
        return makeResponseDto(userVo);
    }

    /**
     * 회원가입
     */
    public int register(UserVo userVo) {
    	return userDao.register(userVo);
    }
    
    
    /**
     * DB요청 반환 DTO
     */
    private UserResponseDto makeResponseDto(UserVo userVo) {
    	return UserResponseDto.builder()
                .id(userVo.getId())
                .userId(userVo.getUserId())
                .nickname(userVo.getNickname())
                .email(userVo.getEmail())
                .role(userVo.getRole())
                .build();
    }

}
