<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f0f2f5;
            font-family: Arial, sans-serif;
        }

        .register-container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 320px;
            text-align: center;
        }

        .register-container h2 {
            margin-bottom: 20px;
        }

        .form-group {
            position: relative;
            margin-bottom: 15px;
        }

        .form-group input {
            width: 100%;
            padding: 10px 40px 10px 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        .toggle-password {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            border: none;
            background: none;
            cursor: pointer;
            padding: 0;
        }

        .toggle-password svg {
            width: 24px;
            height: 24px;
            stroke: #555;
        }
        
        /* 기본 상태: X 표시 숨김 */
		.eye-off .cross {
		    display: inline;
		}
		
		.eye-on .cross {
		    display: none;
		}
        

        .register-container button.submit-btn {
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

        .register-container button.submit-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>회원가입</h2>
    <form action="/user/register" method="post" autocomplete="off">
        <div class="form-group">
            <input type="text" name="userId" placeholder="아이디" required>
        </div>

        <div class="form-group">
            <input id="password" type="password" name="password" placeholder="비밀번호 (4자 ~ 16자)" required>
            <button type="button" id="togglePassword" class="toggle-password">
			    <svg id="eyeIcon" xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-width="2" 
			         stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24" class="eye-off">
			        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
			        <circle cx="12" cy="12" r="3"/>
			        <line class="cross" x1="1" y1="1" x2="23" y2="23"/>
			    </svg>
			</button>
        </div>

        <div class="form-group">
            <input type="text" name="nickname" placeholder="닉네임" required>
        </div>

        <div class="form-group">
            <input type="email" name="email" placeholder="이메일" required>
        </div>

        <button type="submit" class="submit-btn">회원가입</button>
    </form>
</div>

<script>
const passwordInput = document.getElementById('password');
const toggleButton = document.getElementById('togglePassword');
const eyeIcon = document.getElementById('eyeIcon');

passwordInput.addEventListener('input', () => {
    // text 모드일 때만 필터 적용
    if(passwordInput.type === 'text') {
        // 영어, 숫자, 특수문자만 허용
        passwordInput.value = passwordInput.value.replace(/[^a-zA-Z0-9!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/g, '');
    }

    // 한글 자판 시뮬레이션 변환 (원하면 text 모드에서도 그대로 보여주기 가능)
    let value = passwordInput.value.toLowerCase();
    let converted = '';
    for(let char of value){
        converted += engToKorMap[char] || char;
    }
    passwordPreview.textContent = converted;
});


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
</script>
</body>
</html>
