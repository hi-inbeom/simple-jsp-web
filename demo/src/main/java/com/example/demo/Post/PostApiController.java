package com.example.demo.Post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Post.Dto.PostDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post/api")
public class PostApiController {
	private final PostService postService;
	
	/* ===================== 글 작성 프로세스 ===================== */
	/**
	 * 글 작성 처리
	 */
    @PostMapping("/write")
    public String write(@ModelAttribute PostDto dto,
    					HttpSession session) {
    	postService.write(dto, session);
        return "redirect:/post/list";
    }

    /* ===================== 글 수정 프로세스 ===================== */
    /**
     * 글 수정 처리
     */
    @PostMapping("/modify")
    public String modify(@ModelAttribute PostDto dto) {
    	postService.modify(dto);
    	return "redirect:/post/list";
    }
    
    /* ===================== 글 삭제 처리 프로세스 ===================== */
    /**
     * 글 삭제 처리
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
    	postService.delete(id);
    	return "redirect:/post/list";
    }
}
