<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset=UTF-8>
<title>クレジットカード管理</title>
<link href="./Main.css" rel="stylesheet" type="text/css">
<script src="./jquery-3.2.0.min.js"></script>
<script src="./Main.js"></script>
</head>
<body>
	<%	
		Integer userId = (Integer) session.getAttribute("user_id");
		String type = (String) request.getAttribute("type");
		Integer result = (Integer) request.getAttribute("result");
		String alert = (String) request.getAttribute("alert");
		Integer updCnt = (Integer) request.getAttribute("upd_cnt");
		String style = null;
		String message = null;
	%>
	<jsp:include page = "Header.jsp"></jsp:include>
	<br>
	<%
		if (type != null) {
			if (type == "insert" || type == "update" || type == "delete") {
				if (result > 0) {
					style = "complete";
					message = "処理が完了しました";
				} else {
					style = "alert";
					message = "処理に失敗しました";
				}
			} else if (type == "error") {
				style = "alert";
				message = alert;
			}
		}

		if (updCnt != null && updCnt != 0) {
			style = "complete";
			message = "定期支払いを" + updCnt + "件 更新しました";
		}

		if (message != null) {
	%>
	<div class=<%=style%>><%=message%></div>
	<%
		}
	%>
	<div id="page">
		<ul>
			<li><div class="edit-solid icon"></div><a href="BKK010.jsp">記帳</a>
			<li><div class="eye icon"></div><a href="CMM031.jsp">照会</a></li>
			<li><div class="tag icon"></div><a href="CMM033.jsp">用途管理</a></li>
			<li><div class="card icon"></div><a href="CMM032.jsp">口座・カード管理</a></li>
		</ul>
		<div></div>
	</div>

</body>
</html>