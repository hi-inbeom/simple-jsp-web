package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session) {
    	if (session.getAttribute("LoginUser") != null) {
    		return "redirect:/user/mypage";
    	}
    	
        return "index";
    }
}