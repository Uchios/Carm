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
		request.setAttribute("page_name", "▶︎ 定期支払 ▶ 修正︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");
		Integer regId = null;

		if (alert == null) {
			regId = Integer.parseInt(request.getParameter("reg_id"));
		} else {
			regId = (Integer) request.getAttribute("reg_id");
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		EntityRegPayment erp = dcp.getRegPaymentByRegId(regId);

		int cycleUnit = erp.getCycleUnit();
		Integer regMonth = erp.getRegMonth();
		int regDay = erp.getRegDay();
		int regPayment = erp.getRegPayment();
		int useCardId = erp.getCardId();
		String summary = erp.getSummary();
		int invalidFlg = erp.getInvalidFlg();

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
			<input type="hidden" name="sql_type" value="<%="update"%>">
			<input type="hidden" name="reg_id" value="<%=regId%>">
			<input type="radio" name="cycle_unit" value="<%=0%>"
				<%if (cycleUnit == 0) {%> checked <%}%>>
			月に１回
			<input type="radio" name="cycle_unit" value="<%=1%>"
				<%if (cycleUnit == 1) {%> checked <%}%>>
			年に１回
			<h4>支払日</h4>
			<SELECT name="reg_month">
				<option value="<%=0%>" <%if (regMonth == null) {%> selected <%}%>></option>
				<%
					for (int j = 1; j <= 12; j++) {
				%>
				<option value="<%=j%>" <%if (regMonth == j) {%> selected <%}%>><%=j%></option>
				<%
					}
				%>
			</SELECT> 月 <SELECT name="reg_day">
				<%
					for (int j = 1; j <= 28; j++) {
				%>
				<option value="<%=j%>" <%if (regDay == j) {%> selected <%}%>><%=j%></option>
				<%
					}
				%>
				<option value="0" <%if (regDay == 0) {%> selected <%}%>>月末</option>
			</SELECT> 日
			<h4>支払額</h4>
			<input type="text" name="reg_payment" class="mForm"
				value="<%=regPayment%>">
			円
			<h4>カード</h4>
			<SELECT name="card_id">
				<%
					for (int j = 0; j < ecList.size(); j++) {
						int cardId = ecList.get(j).getCardId();
						String cardName = ecList.get(j).getCardName();
				%>
				<option value="<%=cardId%>" <%if (cardId == useCardId) {%> selected
					<%}%>><%=cardName%>
				</option>
				<%
					}
				%>
			</SELECT>
			<h4>摘要</h4>
			<input type="text" name="summary" value="<%=summary%>">
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