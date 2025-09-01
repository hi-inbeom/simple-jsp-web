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
