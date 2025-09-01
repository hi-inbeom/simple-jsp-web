package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.User.Dto.LoginUserRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * 아이디로 유저 조회
     */
    public SessionUser login(LoginUserRequestDto dto) {
		User user = userDao.findByUserId(dto.getUserId());
        
        validatePassword(dto.getPassword(), user.getPassword());

        // user → SessionUser 변환
        return makeSessionUser(user);
    }

    /**
     * 회원가입
     */
    public int register(User user) {
    	return userDao.register(user);
    }
    
    
    /**
     * DB요청 반환 DTO
     */
    private SessionUser makeSessionUser(User user) {
    	return SessionUser.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    /**
     * 비밀번호 검증
     */
    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new UserException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    }
}
