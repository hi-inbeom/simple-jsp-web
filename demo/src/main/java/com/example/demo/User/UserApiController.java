package com.example.demo.User;

import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.User.Dto.JoinUserRequestDto;
import com.example.demo.User.Dto.LoginUserDto;
import com.example.demo.User.Dto.LoginUserRequestDto;
import com.example.demo.User.Dto.UpdateUserRequestDto;
import com.example.demo.User.Dto.UpdateUserResponseDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/api")
public class UserApiController {

	private final UserService userService;

	/* ===================== 로그인/로그아웃 프로세스 ===================== */
	/**
	 * 로그인 처리
	 */
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginUserRequestDto dto, HttpSession session) {
        LoginUserDto loginUserDto = userService.login(dto);

	    // 기존 세션 속성 제거
	    session.removeAttribute("LoginUser");
		session.setAttribute("LoginUser", loginUserDto);

		return "redirect:/";
	}

	/**
	 * 로그아웃
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.removeAttribute("LoginUser");
		return "redirect:/";
	}

	/* ===================== 회원가입 프로세스 ===================== */
	/**
	 * 회원 가입
	 */
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute JoinUserRequestDto dto,
							BindingResult bindingResult,
							Model model,
							HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "user/register";
		}
		
        LoginUserDto loginUserDto = userService.register(dto);

	    // 기존 세션 속성 제거
	    session.removeAttribute("LoginUser");
		session.setAttribute("LoginUser", loginUserDto);

		return "redirect:/";
	}

	/* ===================== 회원탈퇴 프로세스 ===================== */
	/**
	 * 회원 탈퇴
	 */
	@PostMapping("/delete")
	public String delete(HttpSession session) {
		LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute("LoginUser");
		
		if (loginUserDto == null) {
			return "redirect:/";
		}

        userService.deleteUser(loginUserDto.getId());

        // 로그인 세션 제거
	    session.removeAttribute("LoginUser");

        // 탈퇴 후 메인 페이지나 안내 페이지로 이동
        return "redirect:/";
	}
	
	/* ===================== 회원찾기 프로세스 ===================== */
	/**
	 * 회원 찾기
	 */
	@PostMapping("/find")
	public String find(@RequestParam("email") String email, HttpSession session) {
	    if (email == null || email.trim().isEmpty()) {
	        return "redirect:/user/find";
	    }
	    LoginUserDto loginUserDto = userService.find(email);

	    session.setAttribute("LoginUser", loginUserDto);
	    return "redirect:/";
	}

	/* ===================== 회원수정 프로세스 ===================== */
	/**
	 * 회원 수정
	 */
	@PostMapping("/update")
	public String update(@ModelAttribute UpdateUserRequestDto dto,
						HttpSession session,
						Model model) {
		LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute("LoginUser");
		// 로그인 검증
		if (loginUserDto == null) {
			return "redirect:/";
		}
		// 요청 ID랑 로그인 ID 동일성 검증
		if (!(loginUserDto.getUserId().equals(dto.getUserId()))) {
			return "redirect:/";
		}
		
		UpdateUserResponseDto response = new UpdateUserResponseDto();
		
		if (dto.getPassword() != null && dto.getPassword() != "") {
			validatePassword(dto, response);
		}
	    if (dto.getNickname() != null && dto.getNickname() != "") {
		    validateNickname(dto, response);
	    }
	    if (dto.getEmail() != null && dto.getEmail() != "") {
		    validateEmail(dto, response);	
	    }

        loginUserDto = userService.update(dto);

	    // 기존 세션 속성 제거
	    session.removeAttribute("LoginUser");
		session.setAttribute("LoginUser", loginUserDto);
		
		model.addAttribute("response",response);

		return "user/mypage";
	}
	
	private void validatePassword(UpdateUserRequestDto dto, UpdateUserResponseDto response) {
	    Pattern pattern = Pattern.compile("[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]{4,16}");
	    
	    if (!pattern.matcher(dto.getPassword()).matches()) {
	        response.setPasswordMessage("비밀번호는 4~16자입니다.");
	        dto.setPassword(null);
	    } else {
	    	response.setPasswordMessage("변경이 완료되었습니다.");
	    }
	}
	
	private void validateNickname(UpdateUserRequestDto dto, UpdateUserResponseDto response) {
	    Pattern pattern = Pattern.compile("^[a-zA-Z0-9-_]{2,10}$");
	    if (!pattern.matcher(dto.getNickname()).matches()) {
	        response.setNicknameMessage("닉네임은 2~10자입니다.");
	        dto.setNickname(null);
	    } else {
	    	response.setNicknameMessage("변경이 완료되었습니다.");
	    }
	}
	
	private void validateEmail(UpdateUserRequestDto dto, UpdateUserResponseDto response) {
	    Pattern pattern = Pattern.compile("^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$");;
	    if (!pattern.matcher(dto.getEmail()).matches()) {
	        response.setEmailMessage("이메일 형식이 잘못되었습니다.");
	        dto.setEmail(null);
	    } else {
	    	response.setEmailMessage("변경이 완료되었습니다.");
	    }
	}
}