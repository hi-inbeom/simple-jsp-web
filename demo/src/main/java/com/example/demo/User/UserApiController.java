package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.User.Dto.JoinUserRequestDto;
import com.example.demo.User.Dto.LoginUserRequestDto;

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
        SessionUser sessionUser = userService.login(dto);

	    // 기존 세션 속성 제거
	    session.removeAttribute("sessionUser");
		session.setAttribute("sessionUser", sessionUser);

		return "redirect:/";
	}

	/**
	 * 로그아웃
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.removeAttribute("sessionUser");
		return "redirect:/";
	}

	/* ===================== 회원가입 프로세스 ===================== */
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute JoinUserRequestDto dto,
							BindingResult bindingResult,
							Model model,
							HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "user/register";
		}
		
        SessionUser sessionUser = userService.register(dto);

	    // 기존 세션 속성 제거
	    session.removeAttribute("sessionUser");
		session.setAttribute("sessionUser", sessionUser);

		return "redirect:/";
	}
}