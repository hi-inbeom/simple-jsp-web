package com.example.demo.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.User.Dto.JoinUserRequestDto;
import com.example.demo.User.Dto.LoginUserRequestDto;
import com.example.demo.User.Dto.UserResponseDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    
    /* ===================== 로그인/로그아웃 프로세스 ===================== */
    /**
     * 로그인 처리
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginUserRequestDto dto,
                        HttpSession session,
                        Model model) {
    	UserResponseDto userReponseDto = userService.findByUserId(dto.getUserId());

        if (userReponseDto == null) {
			model.addAttribute("error", "존재하지 않는 아이디입니다.");
			return "login";
        }

        session.setAttribute("loginUser", userReponseDto.getUserId());
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

    /* ===================== 회원가입 프로세스 ===================== */
    /**
     * 회원가입 폼 이동
     */
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "register";
    }

    /**
     * 회원가입 처리
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute JoinUserRequestDto dto,
                           HttpSession session,
                           Model model) {
        try {
            userService.register(dto.toVo());
            model.addAttribute("message", "회원가입이 완료되었습니다. 로그인 해주세요.");
            return "login"; // 회원가입 성공 후 로그인 페이지
        } catch (Exception e) {
            model.addAttribute("error", "회원가입 중 오류가 발생했습니다: " + e.getMessage());
            return "register";
        }
    }

    /**
     * 마이페이지
     */
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        UserVo loginUser = (UserVo) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", loginUser);
        return "mypage"; // mypage.jsp
    }


}
