<%@page import="Carm.EntityUse"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Carm.EntityCard"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="Carm.GpDateConversion"%>
<%@ page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.sql.Date"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset=UTF-8>
<title>クレジットカード管理システム</title>
<link href="./Main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
		request.setAttribute("page_name", "▶︎ 利用明細 ▶︎ 修正︎");
		request.setCharacterEncoding("UTF-8");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		Integer useId = Integer.parseInt(request.getParameter("use_id"));
		EntityUse eu = dcp.getUseEntityByUseId(useId);

		Date useDate = eu.getUseDate();
		String summary = eu.getSummary();
		Integer payment = eu.getPayment();
		Integer useCardId = eu.getCardId();

		GpDateConversion gdc = new GpDateConversion(useDate);
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
		<form action="./SvRFR020" method="post">
			<input type="hidden" name="use_id" value="<%=useId%>">
			<h4>日付</h4>
			<input type="text" name="year" value=<%=gdc.year%> class="sForm">
			年
			<input type="text" name="month" value=<%=gdc.month%> class="sForm">
			月
			<input type="text" name="day" value=<%=gdc.day%> class="sForm">
			日
			<h4>金額</h4>
			<input type="text" name="payment" value="<%=payment%>" class="mForm">
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
			<input type="text" name="summary" value="<%=summary%>" class="lForm">
			<br> <br>
			<input type="submit" value="更新" class="decision">
		</form>

	</div>

</body>
</html>