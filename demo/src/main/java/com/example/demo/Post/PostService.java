package com.example.demo.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.Post.Dto.PostDto;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostDao postDao;

    public Map<String, Object> list(int page, int size) {
        int totalCount = postDao.getAllCnt();
        Pagination pagination = new Pagination(page, size, totalCount);

        Map<String, Integer> params = new HashMap<>();
        params.put("startIndex", pagination.getStartIndex());
        params.put("size", pagination.getSize());

        List<Post> posts = postDao.getAll(params);
        List<PostDto> dtoList = posts.stream()
                                     .map(PostDto::fromVo)
                                     .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("posts", dtoList);
        result.put("pagination", pagination);
        return result;
    }


    public PostDto getPost(Long id) {
        Post post = postDao.findById(id);
        if(post == null) return null;
        return PostDto.fromVo(post);
    }


	public void delete(Long id) {
		postDao.delete(id);
	}


	/**
	 * 글 작성
	 * @param dto 
	 * @param session 
	 */
	public void write(PostDto dto, HttpSession session) {
		postDao.write(dto.toVo());
	}

	/**
	 * 글 수정
	 */
	public void modify(PostDto dto) {
		postDao.modify(dto.toVo());
	}
}
