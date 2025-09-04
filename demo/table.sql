CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 기본키
    user_id VARCHAR(50) NOT NULL UNIQUE,        -- 아이디 (중복 불가)
    password VARCHAR(255) NOT NULL,             -- 비밀번호 (암호화 고려, 길게 잡음)
    nickname VARCHAR(100) NOT NULL,             -- 닉네임
    email VARCHAR(100) NOT NULL UNIQUE,         -- 이메일 (중복 불가)
    role TINYINT NOT NULL DEFAULT 1,            -- 권한 (0: 관리자, 1: 일반 회원)
    created_at TIMESTAMP,                       -- 등록일
    updated_at TIMESTAMP                        -- 수정일
);

CREATE TABLE post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 기본키
    post_title VARCHAR(255) NOT NULL,           -- 게시글 제목
    post_content TEXT NOT NULL,                 -- 게시글 내용
    post_view BIGINT NOT NULL DEFAULT 0,        -- 조회수 (기본값 0)
    post_writer VARCHAR(50),                    -- 작성자 (user.user_id와 매칭 가능)
    created_at TIMESTAMP,                       -- 등록일
    updated_at TIMESTAMP                        -- 수정일
);



