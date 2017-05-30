<!-- 定期支払の修正画面 -->
<%@page import="Carm.EntityRegPayment"%>
<%@page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityCard"%>
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
		request.setAttribute("page_name", "▶︎ 定期支払 ▶ 新規作成︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");
		Integer regId = null;

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		List<EntityCard> ecList = dcp.getCardEntity();
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<%
		if (alert != null) {
	%>
	<div class="alert"><%=alert%></div>
	<%
		}
	%>
	<div id="sub_page">
		<form action="./SvRGR040" method="post">
			<h4>支払周期</h4>
			<input type="hidden" name="sql_type" value="<%="insert"%>">
			<input type="radio" name="cycle_unit" value="<%=0%>" checked>
			月に１回
			<input type="radio" name="cycle_unit" value="<%=1%>">
			年に１回
			<h4>支払日</h4>
			<SELECT name="reg_month">
				<option value="<%=0%>"></option>
				<%
					for (int j = 1; j <= 12; j++) {
				%>
				<option value="<%=j%>"><%=j%></option>
				<%
					}
				%>
			</SELECT> 月 <SELECT name="reg_day">
				<%
					for (int j = 1; j <= 28; j++) {
				%>
				<option value="<%=j%>"><%=j%></option>
				<%
					}
				%>
				<option value="0">月末</option>
			</SELECT> 日
			<h4>支払額</h4>
			<input type="text" name="reg_payment" class="mForm">
			円
			<h4>カード</h4>
			<SELECT name="card_id">
				<%
					for (int j = 0; j < ecList.size(); j++) {
						int cardId = ecList.get(j).getCardId();
						String cardName = ecList.get(j).getCardName();
				%>
				<option value="<%=cardId%>"><%=cardName%>
				</option>
				<%
					}
				%>
			</SELECT>
			<h4>摘要</h4>
			<input type="text" name="summary">
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