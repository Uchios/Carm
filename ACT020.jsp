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
		request.setAttribute("page_name", "▶︎ 銀行口座の設定 ▶ 修正︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		
		String alert = (String) request.getAttribute("alert");
		Integer accountId = null;

		if (alert == null) {
			accountId = Integer
					.parseInt(request.getParameter("account_id"));
		} else {
			accountId = (Integer) request.getAttribute("account_id");
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		EntityAccount ea = dcp.getAccountEntityByAccountId(accountId);
		String accountName = ea.getAccountName();
		Integer regHolidayFlg = ea.getRegHolidayFlg();
		Integer invalidFlg = ea.getInvalidFlg();
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
		<form action="./SvACT040" method="post">
			<h4>口座名</h4>
			<input type="hidden" name="sql_type" value="<%="update"%>">
			<input type="hidden" name="account_id" value="<%=accountId%>">
			<input type="text" name="account_name" value="<%=accountName%>">
			<h4>定休日の設定</h4>
			<input type="radio" name="reg_holiday_flg" value="1"
				<%if (regHolidayFlg == 1) {%> checked <%}%>>
			土曜・日曜は引き落としを行わない
			<input type="radio" name="reg_holiday_flg" value="0"
				<%if (regHolidayFlg == 0) {%> checked <%}%>>
			土曜・日曜も引き落としを行う
			<h4>状態</h4>
			<input type="radio" name="invalid_flg" value="0"
				<%if (invalidFlg == 0) {%> checked <%}%>>
			有効
			<input type="radio" name="invalid_flg" value="1"
				<%if (invalidFlg == 1) {%> checked <%}%>>
			無効 <br> <br>
			<input type="submit" value="更新" class="decision">
		</form>

	</div>

</body>
</html>