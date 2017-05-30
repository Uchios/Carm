<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Carm.EntityRegPayment"%>
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
		request.setAttribute("page_name", "▶︎ 定期支払︎");
		Integer userId = (Integer) session.getAttribute("user_id");
		
		if (userId == null) {
			request.setAttribute("message", "timeout");
			RequestDispatcher rd = request
					.getRequestDispatcher("/CMM010.jsp");
			rd.forward(request, response);
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		List<EntityRegPayment> erpList = dcp.getRegPaymentEntity();
		DecimalFormat df = new DecimalFormat("#,###");
	%>
	<jsp:include page = "Header.jsp"></jsp:include>	
	<br>
	<div id="sub_page">
		<input type=button onclick="location.href='./RGR040.jsp'"
			value="+ 新規登録" class="new_button">
		<br>
		<table border="1">
			<tr>
				<th>定期支払日</th>
				<th>支払額</th>
				<th>カード</th>
				<th>摘要</th>
				<th>状態</th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < erpList.size(); i++) {
					int regId = erpList.get(i).getRegId();
					int cycleUnit = erpList.get(i).getCycleUnit();
					Integer regMonth = erpList.get(i).getRegMonth();
					int regDay = erpList.get(i).getRegDay();
					int regPayment = erpList.get(i).getRegPayment();
					String strRegPayment = df.format(regPayment);
					int cardId = erpList.get(i).getCardId();
					String summary = erpList.get(i).getSummary();
					int invalidFlg = erpList.get(i).getInvalidFlg();
					String strPaymentDate = null;
					String strInvalidFlg = null;
					EntityCard ec = dcp.getCardEntityByCardId(cardId);
					String cardName = ec.getCardName();

					if (cycleUnit == 0) {
						strPaymentDate = "毎月";
					} else {
						strPaymentDate = "毎年" + regMonth + "月";
					}

					if (regDay == 0) {
						strPaymentDate += "末";
					} else {
						strPaymentDate += regDay + "日";
					}

					if (invalidFlg == 1) {
						strInvalidFlg = "無効";
					} else {
						strInvalidFlg = "有効";
					}
			%>
			<tr>
				<td><%=strPaymentDate%></td>
				<td><%=strRegPayment%>円</td>
				<td><%=cardName%></td>
				<td><%=summary%></td>
				<td><%=strInvalidFlg%></td>

				<td>
					<form action="./RGR020.jsp" method="post" style="display: inline">
						<input type="hidden" value="<%=regId%>" name="reg_id">
						<input type="submit" value="修正" class="upd_button">
					</form>
					<form action="./SvRGR030" method="post" style="display: inline">
						<input type="hidden" value="<%=regId%>" name="reg_id">
						<input type="submit" value="削除" class="del_button">
					</form>
				</td>
			</tr>
			<%
				}
			%>
		</table>

	</div>

</body>
</html>