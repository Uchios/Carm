<%@page import="Carm.GpDateConversion"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="Carm.EntityCard"%>
<%@ page import="Carm.DaoCarmPersonal"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset=UTF-8>
<title>クレジットカード管理システム</title>
<link href="./Main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%
		Integer userId = (Integer) session.getAttribute("user_id");
		String userName = (String) session.getAttribute("user_name");
		
		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		List<EntityCard> ecList = dcp.getCardEntity();
		GpDateConversion gdc = new GpDateConversion(new java.util.Date());
	%>
	<br>
	<br>
	<form action="./SvBKK010" method="post">
		<h4>日付</h4>
		<input type="text" name="year" value=<%=gdc.year%> class="sForm">
		年
		<input type="text" name="month" value=<%=gdc.month%> class="sForm">
		月
		<input type="text" name="day" value=<%=gdc.day%> class="sForm">
		日
		<h4>金額</h4>
		<input type="text" name="payment" class="mForm">
		円
		<h4>カード</h4>
		<SELECT name="card_id">
			<option value=""></option>
			<%
				for (int j = 0; j < ecList.size(); j++) {
					int cardId = ecList.get(j).getCardId();
					String cardName = ecList.get(j).getCardName();
			%>
			<option value="<%=cardId%>"><%=cardName%></option>
			<%
				}
			%>
		</SELECT>
		<h4>摘要</h4>
		<input type="text" name="summary" class="lForm">
		<br> <br>
		<input type="submit" value="記帳" class="decision">
	</form>

	<br>
	<br>

</body>
</html>