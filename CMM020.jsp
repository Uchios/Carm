<%@page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityAccount"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クレジットカード管理システム</title>
<link href="./Main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
		String message = (String) request.getAttribute("message");
		String alert = (String) request.getAttribute("alert");
		String style = (String) request.getAttribute("style");
	%>
	<div id="sub_page">
		<%
			if (alert != null) {
		%>
		<div class=<%=style%>><%=alert%></div>
		<%
			}
		%>

		<form action="./SvCMM020" method="post">
			<h4>ユーザ名（20文字以内）</h4>
			<input type="text" name="user_name">
			<h4>パスワード（10文字以内）</h4>
			<input type="text" name="password">
			<br> <br> <br>
			<input type="submit" value="ユーザ登録" class="decision">
		</form>

		<br> <br> <br> <br> <a href="./CMM010.jsp">ログイン画面</a>
	</div>

</body>
</html>