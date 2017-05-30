<%@page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityCard"%>
<%@ page import="Carm.EntityAccount"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.DecimalFormat"%>
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
		request.setAttribute("page_name", "▶︎ クレジットカードの設定");
		Integer userId = (Integer) session.getAttribute("user_id");

		if (userId == null) {
			request.setAttribute("message", "timeout");
			RequestDispatcher rd = request
					.getRequestDispatcher("/CMM010.jsp");
			rd.forward(request, response);
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		List<EntityCard> ecList = dcp.getCardEntity();
		DecimalFormat df = new DecimalFormat("#,###");
	%>
	<jsp:include page = "Header.jsp"></jsp:include>	
	<br>
	<div id="sub_page">
		<input type=button onclick="location.href='./CRD040.jsp'"
			value="+ 新規登録" class="new_button">
		<br>
		<table border="1">
			<tr>
				<th>カード名</th>
				<th>締め日</th>
				<th>支払日</th>
				<th>引き落とし口座</th>
				<th>利用限度額</th>
				<th>利用期限</th>
				<th>状態</th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < ecList.size(); i++) {
					int cardId = ecList.get(i).getCardId();
					String cardName = ecList.get(i).getCardName();
					int cutoffDay = ecList.get(i).getCutoffDay();
					int paymentDay = ecList.get(i).getPaymentDay();
					int paymentMonth = ecList.get(i).getPaymentMonth();
					int accountId = ecList.get(i).getAccountId();
					int paymentLimit = ecList.get(i).getPaymentLimit();
					String strPaymentLimit = df.format(paymentLimit);
					Date timeLimit = ecList.get(i).getTimeLimit();
					int invalidFlg = ecList.get(i).getInvalidFlg();
					String strCutoffDay = null;
					String strPaymentMonth = null;
					String paymentMonthDay = null;
					String strInvalidFlg = null;
					EntityAccount ea = dcp.getAccountEntityByAccountId(accountId);
					String accountName = ea.getAccountName();

					if (paymentMonth == 0) {
						strPaymentMonth = "当月";
					} else if (paymentMonth == 1) {
						strPaymentMonth = "翌月";
					} else if (paymentMonth == 2) {
						strPaymentMonth = "翌々月";
					}

					if (cutoffDay == 0) {
						strCutoffDay = "月末";
					} else {
						strCutoffDay = cutoffDay + "日";
					}

					if (paymentDay == 0) {
						paymentMonthDay = strPaymentMonth + "末";
					} else {
						paymentMonthDay = strPaymentMonth + paymentDay + "日";
					}

					if (invalidFlg == 1) {
						strInvalidFlg = "解約済";
					} else {
						strInvalidFlg = "利用中";
					}
			%>
			<tr>
				<td><%=cardName%></td>
				<td><%=strCutoffDay%></td>
				<td><%=paymentMonthDay%></td>
				<td><%=accountName%></td>
				<td><%=strPaymentLimit%>円</td>
				<td><%=timeLimit%></td>
				<td><%=strInvalidFlg%></td>
				<td>
					<form action="./CRD020.jsp" method="post" style="display: inline">
						<input type="hidden" value="<%=cardId%>" name="card_id">
						<input type="submit" value="修正" class="upd_button">
					</form>
					<form action="./SvCRD030" method="post" style="display: inline">
						<input type="hidden" value="<%=cardId%>" name="card_id">
						<input type="submit" value="削除" class="del_button">
					</form>
				</td>
			</tr>
			<%
				}
			%>
		</table>

		<br> <br> <br> <br> <a href="./CMM030.jsp">ホーム</a>
	</div>

</body>
</html>