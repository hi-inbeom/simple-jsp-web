package com.example.demo.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class BoardController {

    // 게시글 목록 페이지
    @GetMapping
    public String list() {
        return "list";
    }

    // 글 작성 페이지
    @GetMapping("/write")
    public String write() {
        return "write";
    }

    // 글 상세보기 페이지
    @GetMapping("/view/{id}")
    public String detail() {
        return "detail";
    }

    // 글 수정 페이지
    @GetMapping("/edit/{id}")
    public String modify() {
        return "modify";    }
}
