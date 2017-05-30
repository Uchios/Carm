<%@ page contentType="text/html; charset=UTF-8"%>
<header>
	<div class='header_left'>
		<a class='header_title' href='./CMM030.jsp'>Payment Card Manager</a> 
		<span class='header_page'>メインメニュー ${page_name}</span>
	</div>
	<div class='header_right'>
		<div class="clock icon"></div>
		ログイン日時：${login_time}<br>
		<div class="profile icon"></div>
		ユーザー：${user_name}さん
		<form action="./SvCMM011" method="post" style='display: inline'>
			<input type="submit" value="ログアウト">
		</form>
	</div>
</header>