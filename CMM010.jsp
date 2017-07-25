<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.DaoCarmAdmin"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset=UTF-8>
<title>クレジットカード管理システム</title>
<link href="./Main.css" rel="stylesheet" type="text/css">

</head>
<body>
	<%
		String message = (String) request.getAttribute("message");
		String style = (String) request.getAttribute("style");

		String displayMessage = null;
		if (message != null) {
			if (message.equals("error")) {
				displayMessage = "ユーザー名とパスワードの組み合わせが間違っています。";
			} else if (message.equals("logout")) {
				displayMessage = "ログアウトしました。";
			} else if (message.equals("timeout")) {
				displayMessage = "タイムアウトしました。再度ログインしてください。";
			} else if (message.equals("systemError")) {
				displayMessage = "システムエラーが発生しました。再度ログインしてください。";
			}
		}
	%>
	<div id="login">
		<h1 class="login_title">Payment Card Manager</h1>
		<%
			if (displayMessage != null) {
		%>
		<div class=<%=style%>><%=displayMessage%></div>
		<%
			}
		%>
		<form action="./SvCMM010" method="post">
			ユーザー名：
			<input type="text" name="user_name">
			<br> パスワード：
			<input type="password" name="password">
			<br> <br>
			<input type="submit" value="ログイン" class="login_button">
		</form>
	</div>
	<p>
		<a href="CMM020.jsp">新規ユーザの作成</a>
	</p>

</body>
</html>