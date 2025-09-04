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
        .post-container {
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
            margin-bottom: 12px;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none; border-radius: 6px;
            cursor: pointer;
            float: right;
        }
        .action-btn:hover { background-color: #45a049; }
        
        
        /* ================== 페이지네이션 ================== */
		.pagination {
		    text-align: center;
		    margin-top: 20px;
		}
		
		/* 페이지 번호 링크 스타일 */
		.pagination a {
		    display: inline-block;
		    margin: 0 5px;
		    padding: 8px 12px;
		    text-decoration: none;
		    color: #4CAF50;
		    border: 1px solid #4CAF50;
		    border-radius: 6px;
		    transition: 0.3s;
		}
		
		/* 링크 호버 효과 */
		.pagination a:hover {
		    background-color: #4CAF50;
		    color: white;
		}
		
		/* 현재 페이지 강조 */
		.pagination strong {
		    display: inline-block;
		    margin: 0 5px;
		    padding: 8px 12px;
		    background-color: #2196F3;
		    color: white;
		    border-radius: 6px;
		}
		        
    </style>
</head>
<body>
<div class="post-container">
    <h2>게시판</h2>
    <button class="action-btn" onclick="location.href='/post/write'">글쓰기</button>
    <table>
        <thead>
            <tr>
                <th style="width:50px;">번호</th>
                <th>제목</th>
                <th style="width:150px;">작성자</th>
                <th style="width:150px;">작성일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <td>${post.id}</td>
                    <td><a href="/post/detail/${post.id}">${post.postTitle}</a></td>
                    <td>${post.postWriter}</td>
                    <td>${post.createdAt}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty posts}">
                <tr>
                    <td colspan="4">등록된 게시물이 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>

	<!-- 페이지네이션 -->
	<div class="pagination">
	    <c:if test="${pagination.page > 1}">
	        <a href="?page=${pagination.page - 1}&size=${pagination.size}">&lt;</a>
	    </c:if>
	
	    <c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="i">
	        <c:choose>
	            <c:when test="${i == pagination.page}">
	                <strong>${i}</strong>
	            </c:when>
	            <c:otherwise>
	                <a href="?page=${i}&size=${pagination.size}">${i}</a>
	            </c:otherwise>
	        </c:choose>
	    </c:forEach>
	
	    <c:if test="${pagination.page < pagination.totalPages}">
	        <a href="?page=${pagination.page + 1}&size=${pagination.size}">&gt;</a>
	    </c:if>
	</div>
</div>
</body>
</html>
