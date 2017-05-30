<%@page import="Carm.EntitySumPayment"%>
<%@page import="Carm.GpDateConversion"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityUse"%>
<%@ page import="Carm.EntityCard"%>
<%@ page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset=UTF-8>
<title>クレジットカード管理</title>
<link href="./Main.css" rel="stylesheet" type="text/css">

</head>
<body>
	<%
		request.setAttribute("page_name", "▶︎ 支払予定日");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");

		List<EntitySumPayment> espList = (ArrayList<EntitySumPayment>) request
				.getAttribute("esp_list");
		Date startDate = (Date) request.getAttribute("start_date");
		Date endDate = (Date) request.getAttribute("end_date");

		GpDateConversion gdc1 = new GpDateConversion(startDate);
		GpDateConversion gdc2 = new GpDateConversion(endDate);

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		DecimalFormat df = new DecimalFormat("#,###");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd (E)");
	%>
	<jsp:include page = "Header.jsp"></jsp:include>	
	<div id="sub_page">
		<%
			if (alert != null) {
		%>
		<div class="alert"><%=alert%></div>
		<%
			}
		%>
		<br>表示範囲を指定↓
		<form action="./SvCAL010" method="post">
			<input type="hidden" name="search_flg" value="1">
			<input type="text" name="start_year" value=<%=gdc1.year%>
				class="sForm">
			年
			<input type="text" name="start_month" value=<%=gdc1.month%>
				class="sForm">
			月
			<input type="text" name="start_day" value=<%=gdc1.day%> class="sForm">
			日 〜
			<input type="text" name="end_year" value=<%=gdc2.year%> class="sForm">
			年
			<input type="text" name="end_month" value=<%=gdc2.month%>
				class="sForm">
			月
			<input type="text" name="end_day" value=<%=gdc2.day%> class="sForm">
			日
			<input type="submit" value="検索">
		</form>
		<%
			for (int i = 0; i < espList.size(); i++) {
				int sumPayment = espList.get(i).getSumPayment();
				String strSumPayment = df.format(sumPayment);
				Date paymentDate = espList.get(i).getPaymentDate();
				String strPaymentDate = sdf.format(paymentDate);
				int accountId = espList.get(i).getAccountId();
				String accountName = dcp.getAccountEntityByAccountId(accountId)
						.getAccountName();
				boolean exPaymentDateFlg = false;
				boolean nextPaymentDateFlg = false;

				// 前の支払日と同じ場合、フラグを立てる
				if (i > 0) {
					Date exPaymentDate = espList.get(i - 1).getPaymentDate();
					if (paymentDate.compareTo(exPaymentDate) == 0) {
						exPaymentDateFlg = true;
					}
				}
				// 次の支払日と同じ場合、フラグを立てる
				if (i < espList.size() - 1) {
					Date nextPaymentDate = espList.get(i + 1).getPaymentDate();
					if (paymentDate.compareTo(nextPaymentDate) == 0) {
						nextPaymentDateFlg = true;
					}
				}

				if (!exPaymentDateFlg) {
		%>
		<h3><%=strPaymentDate%></h3>
		<table border="1">
			<tr>
				<th class='cal_act'>銀行口座</th>
				<th class='cal_pmt'>支払額</th>
			</tr>
			<%
				}
			%>
			<tr>
				<td><%=accountName%></td>
				<td><%=strSumPayment%>円</td>
			</tr>
			<%
				if (!nextPaymentDateFlg) {
			%>
		</table>
		<%
			}
			}
		%>
		<br> <br> <br> <br> <a href="./CMM030.jsp">ホーム</a>
	</div>

</body>
</html>