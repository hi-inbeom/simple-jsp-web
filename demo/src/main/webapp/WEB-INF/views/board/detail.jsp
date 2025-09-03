<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시글 상세보기</title>
    <style>
        body {
            margin: 0; padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
        }
        .detail-container {
            width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 { text-align: center; margin-bottom: 20px; }
        .post-info {
            margin-bottom: 20px;
        }
        .post-info span {
            display: inline-block;
            margin-right: 20px;
            font-weight: bold;
        }
        .post-content {
            padding: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            min-height: 200px;
            white-space: pre-wrap;
            background-color: #f9f9f9;
        }
        .action-btn {
            margin-top: 20px;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none; border-radius: 6px;
            cursor: pointer;
            margin-right: 10px;
        }
        .action-btn:hover { background-color: #45a049; }
        .delete-btn {
            background-color: #f44336;
        }
        .delete-btn:hover { background-color: #d32f2f; }
    </style>
</head>
<body>
<div class="detail-container">
    <h2>${post.title}</h2>
    <div class="post-info">
        <span>작성자: ${post.author}</span>
        <span>작성일: ${post.createdDate}</span>
    </div>
    <div class="post-content">
        ${post.content}
    </div>

    <div>
        <button class="action-btn" onclick="location.href='/posts'">목록으로</button>

        <!-- 작성자인 경우에만 수정/삭제 버튼 표시 -->
        <c:if test="${sessionUser.userId == post.authorId}">
            <button class="action-btn" onclick="location.href='/posts/edit/${post.id}'">수정</button>
            <form action="/posts/delete/${post.id}" method="post" style="display:inline;">
                <button type="submit" class="delete-btn" onclick="return confirm('정말 삭제하시겠습니까?')">삭제</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
