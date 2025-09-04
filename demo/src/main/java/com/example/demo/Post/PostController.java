package com.example.demo.Post;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Post.Dto.PostDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
	private final PostService postService;
    // 게시글 목록 페이지
	@GetMapping("/list")
	public String list(@RequestParam(name="page", defaultValue="1") int page,
	                   @RequestParam(name="size", defaultValue="10") int size,
	                   Model model) {
	    Map<String, Object> data = postService.list(page, size);
	    model.addAttribute("posts", data.get("posts"));
	    model.addAttribute("pagination", data.get("pagination"));
	    return "post/list";
	}
    // 글 작성 페이지
    @GetMapping("/write")
    public String write(HttpSession session) {
        return "post/write";
    }

    // 글 상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id,
    					 Model model) {
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post/detail";
    }
    
    // 글 수정 페이지
    @GetMapping("/edit/{id}")
    public String modify(@PathVariable("id") Long id,
			 			 Model model) {
    	PostDto post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post/modify";
    }
}
