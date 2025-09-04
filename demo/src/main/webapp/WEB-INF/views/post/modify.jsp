<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 수정</title>
    <style>
        body {
            margin: 0; padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
        }
        .modify-container {
            width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        h2 { text-align: center; margin-bottom: 20px; }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group input,
        .form-group textarea {
            width: 100%; padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 16px;
        }
        textarea { height: 200px; resize: vertical; }
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
    </style>
</head>
<body>
<div class="modify-container">
    <h2>게시글 수정</h2>
	<form action="/post/api/modify" method="post">
		<input type="hidden" name="id" value="${post.id}">
		<input type="hidden" name="postWriter" value="${LoginUser.userId}">
	    <div class="form-group">
	        <input type="text" name="postTitle" placeholder="제목" value="${post.postTitle}" required>
	    </div>
	    <div class="form-group">
	        <textarea name="postContent" placeholder="내용" required>${post.postContent}</textarea>
	    </div>
	    <button type="submit" class="action-btn">수정 완료</button>
	    <button type="button" class="action-btn" onclick="location.href='/post/detail/${post.id}'">취소</button>
	</form>
</div>
</body>
</html>
