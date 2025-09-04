package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.User.Dto.JoinUserRequestDto;
import com.example.demo.User.Dto.LoginUserDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	/**
	 * 회원가입 화면
	 */
	@GetMapping("/register")
	public String registerPage(Model model) {
	    model.addAttribute("joinUserRequestDto", new JoinUserRequestDto());
		return "user/register";
	}

	/**
	 * 계정 찾기 화면
	 */
	@GetMapping("/find")
	public String findAccountPage() {
		return "user/find";
	}

	/**
	 * 마이페이지 화면
	 */
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
	    LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute("LoginUser");

	    if (loginUserDto == null) {
	        return "redirect:/";
	    }
		return "user/mypage";
	}
}