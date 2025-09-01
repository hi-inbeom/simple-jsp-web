<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;  /* 수평 중앙 */
            align-items: center;      /* 수직 중앙 */
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }

        .login-container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 300px;
            text-align: center;
        }

        .login-container h2 {
            margin-bottom: 20px;
        }

        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .login-container button {
            width: 100%;
            padding: 10px;
            margin-top: 12px;
            background-color: #4CAF50;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<jsp:include page="test.jsp" />
<div class="login-container">
    <h2>로그인</h2>
    <form action="/user/login" method="post">
        <input type="text" name="userId" placeholder="아이디" required><br>
        <input type="password" name="password" placeholder="비밀번호" required><br>
        <button type="submit">로그인</button>
    </form>
    <hr>
    <p>아직 계정이 없으신가요?</p>
    <form action="/user/register" method="get">
        <button type="submit">회원가입</button>
    </form>
</div>

</body>
</html>
