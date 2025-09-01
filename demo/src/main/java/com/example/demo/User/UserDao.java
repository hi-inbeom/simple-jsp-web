package com.example.demo.User;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final SqlSession sqlSession;

    private static final String NAMESPACE = "UserMapper.";

    /**
     * 아이디로 유저 조회
     */
    public UserVo findByUserId(String userId) {
        return sqlSession.selectOne(NAMESPACE + "findByUserId", userId);
    }

    /**
     * 회원가입
     * @return 
     */
    public int register(UserVo userVo) {
        return sqlSession.insert(NAMESPACE + "save", userVo);
    }

    /**
     * 모든 유저 조회
     */
    public List<UserVo> findAll() {
        return sqlSession.selectList(NAMESPACE + "findAll");
    }

    /**
     * 유저 업데이트
     */
    public int update(UserVo userVo) {
        return sqlSession.update(NAMESPACE + "update", userVo);
    }

    /**
     * 유저 삭제
     */
    public int delete(Long id) {
        return sqlSession.delete(NAMESPACE + "delete", id);
    }
}
