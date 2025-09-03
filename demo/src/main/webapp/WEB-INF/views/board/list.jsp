<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판</title>
    <style>
        body {
            margin: 0; padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
        }
        .board-container {
            width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 { text-align: center; margin-bottom: 20px; }
        table {
            width: 100%; border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc; padding: 8px;
            text-align: center;
        }
        th { background-color: #f4f4f4; }
        tr:hover { background-color: #f9f9f9; }
        .action-btn {
            margin-top: 12px;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none; border-radius: 6px;
            cursor: pointer;
        }
        .action-btn:hover { background-color: #45a049; }
    </style>
</head>
<body>
<div class="board-container">
    <h2>게시판</h2>
    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <td>${post.id}</td>
                    <td><a href="/posts/view/${post.id}">${post.title}</a></td>
                    <td>${post.author}</td>
                    <td>${post.createdDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty posts}">
                <tr>
                    <td colspan="4">등록된 게시물이 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
    <button class="action-btn" onclick="location.href='/posts/write'">글쓰기</button>
</div>
</body>
</html>
