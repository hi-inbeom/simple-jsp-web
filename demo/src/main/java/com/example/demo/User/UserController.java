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
    
    /* ===================== 화면이동 ===================== */


    /**
     * 회원가입 화면
     */
    @GetMapping("/register")
    public String registerPage() {
        return "user/register"; // register.jsp
    }

    /**
     * 계정 찾기 화면
     */
    @GetMapping("/find")
    public String findAccountPage() {
        return "user/findAccount"; // findAccount.jsp
    }

    /**
     * 마이페이지 화면
     */
    @GetMapping("/mypage")
    public String mypage(Model model) {
        // 테스트용 더미 유저 정보
        UserVo user = new UserVo();
        user.setNickname("홍길동");
        user.setEmail("test@test.com");
        model.addAttribute("user", user);

        return "user/mypage"; // mypage.jsp
    }
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
}