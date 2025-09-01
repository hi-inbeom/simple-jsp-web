package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.User.Dto.LoginUserRequestDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	/* ===================== 화면이동 ===================== */

	/**
	 * 회원가입 화면
	 */
	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}

	/**
	 * 계정 찾기 화면
	 */
	@GetMapping("/find")
	public String findAccountPage() {
		return "user/findAccount";
	}

	/**
	 * 마이페이지 화면
	 */
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		return "user/mypage";
	}

	/* ===================== 로그인/로그아웃 프로세스 ===================== */
	/**
	 * 로그인 처리
	 */
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute LoginUserRequestDto dto, HttpSession session) {
//        SessionUser sessionUser = userService.login(dto);

	    // 기존 세션 속성 제거
	    session.removeAttribute("sessionUser");
		session.setAttribute("sessionUser", SessionUser.tempUser());
		
		System.out.println(session.getAttribute("sessionUser"));

		return "redirect:/";
	}

	/**
	 * 로그아웃
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}