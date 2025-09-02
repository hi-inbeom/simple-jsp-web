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
        
        .login-form {
        	margin-bottom: 0px;
        }

        .login-container h2 {
            margin-bottom: 20px;
        }
        
        .password-container {
        	display:flex;
        	position: relative;
        }

        .login-container input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        
        
        .toggle-password {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            border: none; background: none; cursor: pointer;
            padding: 0;
        }
        .toggle-password svg {
            width: 24px; height: 24px; stroke: #555;
        }
        .eye-off line.cross { display: block; }  /* 기본 X 표시 */
        .eye-on line.cross { display: none; }    /* 눈 열림 시 X 숨김 */

        .login-btn,
        .register-btn {
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

        .login-btn:hover,
        .register-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>로그인</h2>
    <form class="login-form" action="/user/api/login" method="post">
        <input type="text" name="userId" placeholder="아이디" required><br>
        <div class="password-container">
	        <input type="password" id="password" name="password" placeholder="비밀번호" required><br>
	        <button type="button" id="togglePassword" class="toggle-password">
	            <svg id="eyeIcon" xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-width="2"
	                 stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24" class="eye-off">
	                <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
	                <circle cx="12" cy="12" r="3"/>
	                <line class="cross" x1="1" y1="1" x2="23" y2="23"/>
	            </svg>
	        </button>
        </div>
        <button class="login-btn" type="submit">로그인</button>
    </form>
    <form action="/user/find" method="get">
        <button class="register-btn" type="submit">계정찾기</button>
    </form>
    <hr>
    <p>아직 계정이 없으신가요?</p>
    <form action="/user/register" method="get">
        <button class="register-btn" type="submit">회원가입</button>
    </form>
</div>

</body>
</html>
<script>
	const passwordInput = document.getElementById('password');
	const toggleButton = document.getElementById('togglePassword');
	const eyeIcon = document.getElementById('eyeIcon');
	
	// 비밀번호 보이기/숨기기 토글
	toggleButton.addEventListener('click', () => {
	    if(passwordInput.type === 'password') {
	        passwordInput.type = 'text';
	        eyeIcon.classList.remove('eye-off');
	        eyeIcon.classList.add('eye-on');
	    } else {
	        passwordInput.type = 'password';
	        eyeIcon.classList.remove('eye-on');
	        eyeIcon.classList.add('eye-off');
	    }
	});
	
	// 비밀번호 입력 필터 (text 모드에서만 허용)
	passwordInput.addEventListener('input', () => {
	    if(passwordInput.type === 'text') {
	        passwordInput.value = passwordInput.value.replace(/[^a-zA-Z0-9!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/g, '');
	    }
	});
</script>
