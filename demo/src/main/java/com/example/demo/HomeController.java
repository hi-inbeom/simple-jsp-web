package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session) {
    	if (session.getAttribute("sessionUser") != null) {
    		return "user/mypage";
    	}
    	
        return "index";
    }
}