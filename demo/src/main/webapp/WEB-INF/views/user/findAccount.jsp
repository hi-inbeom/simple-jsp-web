<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>계정 찾기</title>
    <style>
        body {
            margin: 0; padding: 0; height: 100vh;
            display: flex; justify-content: center; align-items: center;
            background-color: #f0f2f5; font-family: Arial, sans-serif;
        }
        .find-container {
            background: #fff; padding: 30px;
            border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 300px; text-align: center;
        }
        .find-container h2 { margin-bottom: 20px; }
        .find-container input {
            width: 100%; padding: 10px; margin: 8px 0;
            border: 1px solid #ccc; border-radius: 6px;
        }
        .find-container button {
            width: 100%; padding: 10px; margin-top: 12px;
            background-color: #4CAF50; border: none;
            color: white; font-size: 16px; border-radius: 6px;
            cursor: pointer;
        }
        .find-container button:hover { background-color: #45a049; }
        .find-container p { margin-top: 12px; }
    </style>
</head>
<body>
<div class="find-container">
    <h2>계정 찾기</h2>
    <form action="/user/findId" method="post">
        <input type="email" name="email" placeholder="가입된 이메일" required><br>
        <button type="submit">아이디 찾기</button>
    </form>
    <form action="/user/findPwd" method="post">
        <input type="text" name="userId" placeholder="아이디" required><br>
        <input type="email" name="email" placeholder="가입된 이메일" required><br>
        <button type="submit">비밀번호 재설정</button>
    </form>
    <hr>
    <p>로그인 화면으로 돌아가기</p>
    <form action="/" method="get">
        <button type="submit">로그인</button>
    </form>
</div>
</body>
</html>
