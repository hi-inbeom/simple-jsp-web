package com.example.demo.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.User.Dto.JoinUserRequestDto;
import com.example.demo.User.Dto.LoginUserDto;
import com.example.demo.User.Dto.LoginUserRequestDto;
import com.example.demo.User.Dto.UpdateUserRequestDto;
import com.example.demo.exception.UserException;

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
    public LoginUserDto login(LoginUserRequestDto dto) {
		User user = userDao.findByUserId(dto.getUserId());
        
        validatePassword(dto.getPassword(), user.getPassword());

        // user → LoginUser 변환
        return makeLoginUser(user);
    }

    /**
     * 회원가입
     */
    public LoginUserDto register(JoinUserRequestDto dto) {
    	validateDuplicationUserId(dto.getUserId());
    	userDao.register(dto.toVo());
    	User user = userDao.findByUserId(dto.getUserId());
    	return makeLoginUser(user);
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
	public LoginUserDto update(@Valid UpdateUserRequestDto dto) {
		userDao.update(dto.toVo());
		User user = userDao.findByUserId(dto.getUserId());
		return makeLoginUser(user);
	}
	
	/**
	 * 계정찾기
	 * @return 
	 */
	public LoginUserDto find(String email) {
		User user = userDao.findByEmail(email);
		return makeLoginUser(user);
	}
    
    
    /**
     * VO -> DTO
     */
    private LoginUserDto makeLoginUser(User user) {
    	return LoginUserDto.builder()
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
