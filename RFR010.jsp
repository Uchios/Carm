<%@page import="java.text.DecimalFormat"%>
<%@page import="Carm.GpDateConversion"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityUse"%>
<%@ page import="Carm.EntityCard"%>
<%@ page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset=UTF-8>
<title>クレジットカード管理</title>
<link href="./Main.css" rel="stylesheet" type="text/css">

</head>
<body>
	<%
		request.setAttribute("page_name", "▶︎ 利用明細︎");	
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");

		Integer sumPayment = (Integer) request.getAttribute("sum_payment");

		List<EntityUse> euList = (ArrayList<EntityUse>) request
				.getAttribute("eu_list");
		List<EntityCard> ecList = (ArrayList<EntityCard>) request
				.getAttribute("ec_list");
		Date endDate = (Date) request.getAttribute("end_date");
		Date startDate = (Date) request.getAttribute("start_date");

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
		<br>利用日時の表示範囲↓
		<form action="./SvRFR010" method="post">
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
			日 <br> <br> カードを絞り込む <SELECT name="card_id"
				onChange="this.form.submit()">
				<option value="-1"></option>
				<option value="-1">全て</option>
				<%
					for (int j = 0; j < ecList.size(); j++) {
				%>
				<option value="<%=ecList.get(j).getCardId()%>"><%=ecList.get(j).getCardName()%>
				</option>
				<%
					}
				%>
			</SELECT>
		</form>
		<br> 【利用合計】 <span class="sum"><%=df.format(sumPayment)%>円</span>
		<table border="1">
			<tr>
				<th>利用日時</th>
				<th>摘要</th>
				<th>支払金額</th>
				<th>カード</th>
				<th>引き落とし予定日</th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < euList.size(); i++) {
					int useId = euList.get(i).getUseId();
					Date useDate = euList.get(i).getUseDate();
					String strUseDate = sdf.format(useDate);
					String summary = euList.get(i).getSummary();
					int payment = euList.get(i).getPayment();
					String strPayment = df.format(payment);
					int cardId = euList.get(i).getCardId();
					String useCardName = dcp.getCardEntityByCardId(cardId)
							.getCardName();
					Date paymentDate = euList.get(i).getPaymentDate();
					String strPaymentDate = sdf.format(paymentDate);
			%>
			<tr>
				<td><%=strUseDate%></td>
				<td><%=summary%></td>
				<td><%=strPayment%>円</td>
				<td><%=useCardName%></td>
				<td><%=strPaymentDate%></td>
				<td>
					<form action="./RFR020.jsp" method="post" style="display: inline">
						<input type="hidden" value="<%=useId%>" name="use_id">
						<input type="submit" value="修正" class="upd_button">
					</form>
					<form action="./SvRFR030" method="post" style="display: inline">
						<input type="hidden" value="<%=useId%>" name="use_id">
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