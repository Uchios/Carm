<!-- クレジットカードの登録画面 -->
<%@page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityCard"%>
<%@ page import="Carm.EntityAccount"%>
<%@ page import="java.sql.Date"%>
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
		request.setAttribute("page_name", "▶︎ クレジットカードの設定 ▶ 新規登録︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);

		List<EntityAccount> eaList = dcp.getAccountEntity();
	%>
	<jsp:include page = "Header.jsp"></jsp:include>	
	<%
		if (alert != null) {
	%>
	<div class="alert"><%=alert%></div>
	<%
		}
	%>
	<div id="sub_page">
		<form action="./SvCRD040" method="post">
			<input type="hidden" name="sql_type" value="<%="insert"%>">
			<h4>カードの名前</h4>
			<input type="text" name="card_name">

			<h4>締め日</h4>
			<SELECT name="cutoff_day">
				<%
					for (int j = 1; j <= 28; j++) {
				%>
				<option value="<%=j%>"><%=j%>
				</option>
				<%
					}
				%>
				<option value="0">月末</option>
			</SELECT>

			<h4>引き落とし日</h4>
			<SELECT name="payment_month">
				<option value="1" selected>翌月</option>
				<option value="2">翌々月</option>
				<option value="0">当月</option>
			</SELECT> <SELECT name="payment_day">
				<%
					for (int j = 1; j <= 28; j++) {
				%>
				<option value="<%=j%>"><%=j%></option>
				<%
					}
				%>
				<option value="0">月末</option>
			</SELECT>

			<h4>引き落とし口座</h4>
			<SELECT name="account_id">
				<%
					for (int i = 0; i < eaList.size(); i++) {
						int accountId = eaList.get(i).getAccountId();
						String accountName = eaList.get(i).getAccountName();
				%>
				<option value="<%=accountId%>"><%=accountName%>
				</option>
				<%
					}
				%>
			</SELECT>

			<h4>限度額</h4>
			<input type="text" name="payment_limit">
			円

			<h4>有効期限</h4>
			<input type="text" name="time_limit">

			<h4>状態</h4>
			<input type="radio" name="invalid_flg" value="0" checked>
			有効
			<input type="radio" name="invalid_flg" value="1">
			無効 <br> <br>
			<input type="submit" value="登録" class="decision">
		</form>

	</div>

</body>
</html>