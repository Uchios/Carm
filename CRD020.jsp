<!-- クレジットカードの修正画面 -->
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
		request.setAttribute("page_name", "▶︎ クレジットカードの設定 ▶ 修正︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");
		Integer cardId = null;

		if (alert == null) {
			cardId = Integer.parseInt(request.getParameter("card_id"));
		} else {
			cardId = (Integer) request.getAttribute("card_id");
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);

		EntityCard ec = dcp.getCardEntityByCardId(cardId);

		String cardName = ec.getCardName();
		int cutoffDay = ec.getCutoffDay();
		int paymentDay = ec.getPaymentDay();
		int paymentMonth = ec.getPaymentMonth();
		int accountId = ec.getAccountId();
		int paymentLimit = ec.getPaymentLimit();
		Date timeLimit = ec.getTimeLimit();
		int invalidFlg = ec.getInvalidFlg();
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
		<h4>*締め日や引き落とし日を修正しても、過去の利用明細の支払日は更新されません。</h4>
		<form action="./SvCRD040" method="post">
			<input type="hidden" name="sql_type" value="<%="update"%>">
			<input type="hidden" name="card_id" value="<%=cardId%>">
			<h4>カードの名前</h4>
			<input type="text" name="card_name" value="<%=cardName%>">

			<h4>締め日</h4>
			<SELECT name="cutoff_day">
				<%
					for (int j = 1; j <= 28; j++) {
				%>
				<option value="<%=j%>" <%if (cutoffDay == j) {%> selected <%}%>>
					<%=j%>
				</option>
				<%
					}
				%>
				<option value="0" <%if (cutoffDay == 0) {%> selected <%}%>>
					月末</option>
			</SELECT>

			<h4>引き落とし日</h4>
			<SELECT name="payment_month">
				<option value="1" <%if (paymentMonth == 1) {%> selected <%}%>>
					翌月</option>
				<option value="2" <%if (paymentMonth == 2) {%> selected <%}%>>
					翌々月</option>
				<option value="0" <%if (paymentMonth == 0) {%> selected <%}%>>
					当月</option>
			</SELECT> <SELECT name="payment_day">
				<%
					for (int j = 1; j <= 28; j++) {
				%>
				<option value="<%=j%>" <%if (paymentDay == j) {%> selected <%}%>><%=j%></option>
				<%
					}
				%>
				<option value="0" <%if (paymentDay == 0) {%> selected <%}%>>月末</option>
			</SELECT>

			<h4>引き落とし口座</h4>
			<SELECT name="account_id">
				<%
					for (int i = 0; i < eaList.size(); i++) {
						int accountId2 = eaList.get(i).getAccountId();
						String accountName = eaList.get(i).getAccountName();
				%>
				<option value="<%=accountId2%>" <%if (accountId == accountId2) {%>
					selected <%}%>>
					<%=accountName%>
				</option>
				<%
					}
				%>
			</SELECT>

			<h4>限度額</h4>
			<input type="text" name="payment_limit" value=<%=paymentLimit%>>
			円

			<h4>有効期限</h4>
			<input type="text" name="time_limit" value=<%=timeLimit%>>

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