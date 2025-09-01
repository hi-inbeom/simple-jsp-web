<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원정보</title>
    <style>
    	/* 가운데 정렬 */
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
        
        form {
		    margin: 0;
		    padding: 0;
		    border: 0;
		    background: none;
		}
        
        /* 메인 창 */
        .profile-container {
            background: #fff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 320px;
        }
        /* 환영 문구 */
        .profile-container h2 {
            text-align: center; margin-bottom: 20px;
        }
        
        .profile-row {
            margin-bottom: 12px;
            position: relative; /* 토글 버튼 위치를 위한 relative */
            display: flex;
        }
        .profile-row label {
            margin-bottom: 4px;
            font-weight: bold;
            width : 100px;
        }
        .profile-row input {
            width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 6px;
        }
        input:disabled { background-color: #eee; }

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

        .section-divider {
            border-top: 1.2px solid #ccc; margin: 8px 0;
        }

        /* 버튼 스타일 */
        .edit-btn,
        .action-btn,
        .delete-btn {
            width: 100%; padding: 10px; margin: 5px 0;
            border: none; border-radius: 6px;
            color: white; cursor: pointer;
        }
        
        .edit-btn,
        .action-btn {
        	 background-color: #4CAF50;
        }
        .edit-btn:hover, .action-btn:hover { background-color: #45a049; }

        .delete-btn {
        	background-color: #f44336;
        }
        .delete-btn:hover { background-color: #d32f2f; }
    </style>
</head>
<body>

<div class="profile-container">
    <h2>환영합니다, <strong>${user.nickname}</strong>님!</h2>
    
    <form id="profileForm" action="/user/update" method="post" autocomplete="off">
        <!-- 닉네임 -->
        <div class="profile-row">
            <label for="nickname">닉네임</label>
            <input type="text" id="nickname" name="nickname" value="${user.nickname}" disabled>
        </div>
        <!-- 이메일 -->
        <div class="profile-row">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" value="${user.email}" disabled>
        </div>
        <!-- 비밀번호 -->
        <div class="profile-row">
            <label for="password">비밀번호</label>
            <input id="password" type="password" name="password" placeholder="비밀번호 (4자 ~ 16자)" disabled>
            <button type="button" id="togglePassword" class="toggle-password">
                <svg id="eyeIcon" xmlns="http://www.w3.org/2000/svg" fill="none" stroke="currentColor" stroke-width="2"
                     stroke-linecap="round" stroke-linejoin="round" viewBox="0 0 24 24" class="eye-off">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/>
                    <circle cx="12" cy="12" r="3"/>
                    <line class="cross" x1="1" y1="1" x2="23" y2="23"/>
                </svg>
            </button>
        </div>

        <!-- 변경하기 버튼 -->
        <button type="button" id="editBtn" class="edit-btn">변경하기</button>
    </form>

    <div class="section-divider"></div>

    <!-- 게시물 보기 버튼 -->
    <button class="action-btn" onclick="location.href='/posts/all'">모든 게시물 보기</button>
    <button class="action-btn" onclick="location.href='/posts/my'">작성 게시물 보기</button>

    <div class="section-divider"></div>

    <!-- 회원 탈퇴 -->
    <form id="deleteForm" action="/user/delete" method="post">
        <button type="button" id="deleteBtn" class="delete-btn">회원 탈퇴</button>
    </form>
</div>

<script>
    const editBtn = document.getElementById('editBtn');
    const inputs = document.querySelectorAll('#profileForm input');
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

    // 변경/저장 버튼
    editBtn.addEventListener('click', () => {
        const isDisabled = Array.from(inputs).some(input => input.disabled);
        if(isDisabled) {
            inputs.forEach(input => input.disabled = false);
            editBtn.textContent = '저장';
        } else {
            document.getElementById('profileForm').submit();
        }
    });

    // 회원 탈퇴
    const deleteBtn = document.getElementById('deleteBtn');
    const deleteForm = document.getElementById('deleteForm');
    deleteBtn.addEventListener('click', () => {
        if(confirm('정말 회원 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
            deleteForm.submit();
        }
    });
</script>

</body>
</html>
