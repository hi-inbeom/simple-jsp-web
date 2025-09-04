package com.example.demo.Post;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.demo.Post.Dto.PostDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostDao {
    private final SqlSession sqlSession;
    private static final String NAMESPACE = "PostMapper.";

    
    /**
     * 모든 게시물 개수 가져오기
     */
    public int getAllCnt() {
    	return sqlSession.selectOne(NAMESPACE+"findAllCnt");
    }
    
    /**
     * 페이지네이션 게시물 가져오기
     */
    public List<Post> getAll(Map params) {
        return sqlSession.selectList(NAMESPACE + "findAll", params);
    }

    /**
     * 게시물 하나 가져오기
     */
    public Post findById(Long id) {
        return sqlSession.selectOne(NAMESPACE + "findById", id);
    }

    /**
     * 게시물 삭제하기
     */
	public int delete(Long id) {
        return sqlSession.delete(NAMESPACE + "delete", id);
	}

	/**
	 * 게시물 작성하기
	 */
	public int write(Post post) {
		return sqlSession.insert(NAMESPACE + "insert", post);
	}

	public int modify(Post post) {
		return sqlSession.update(NAMESPACE + "update", post);
	}
}
