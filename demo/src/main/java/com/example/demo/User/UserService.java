package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.User.Dto.JoinUserRequestDto;
import com.example.demo.User.Dto.LoginUserRequestDto;
import com.example.demo.User.Dto.UpdateUserRequestDto;

import jakarta.validation.Valid;
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
    public SessionUser register(JoinUserRequestDto dto) {
    	validateDuplicationUserId(dto.getUserId());
    	userDao.register(dto.toVo());
    	User user = userDao.findByUserId(dto.getUserId());
    	return makeSessionUser(user);
    }
    
    /**
     * 회원 탈퇴
     */
	public void deleteUser(Long id) {
		userDao.delete(id);
	}
	
	/**
	 * 회원 수정
	 */
	public SessionUser update(@Valid UpdateUserRequestDto dto) {
		userDao.update(dto.toVo());
		User user = userDao.findByUserId(dto.getUserId());
		return makeSessionUser(user);
	}
    
    
    /**
     * VO -> DTO
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
     * 아이디 중복 검증(회원가입)
     */
    private void validateDuplicationUserId(String userId) {
    	User user = userDao.findByUserId(userId);
        if (user != null) {
            throw new UserException("존재하는 아이디입니다.");
        }
    }
    /**
     * 아이디 중복 검증(계정찾기)
     */
    private void validateExistUserId(String userId) {
    	User user = userDao.findByUserId(userId);
        if (user == null) {
            throw new UserException("존재하지 않는 아이디입니다.");
        }
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
