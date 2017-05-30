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
		request.setAttribute("page_name", "▶︎ 銀行口座の設定");
		Integer userId = (Integer) session.getAttribute("user_id");

		if (userId == null) {
			request.setAttribute("message", "timeout");
			RequestDispatcher rd = request
					.getRequestDispatcher("/CMM010.jsp");
			rd.forward(request, response);
		}

		DaoCarmPersonal dcp = new DaoCarmPersonal(userId);
		List<EntityAccount> eaList = dcp.getAccountEntity();
	%>
	<jsp:include page = "Header.jsp"></jsp:include>
	<br>
	<div id="sub_page">
		<input type=button onclick="location.href='./ACT040.jsp'"
			value="+ 新規登録" class="new_button">
		<br>
		<table border="1">
			<tr>
				<th>銀行口座名</th>
				<th>定休日の設定</th>
				<th>状態</th>
				<th></th>
			</tr>
			<%
				for (int i = 0; i < eaList.size(); i++) {
					int accountId = eaList.get(i).getAccountId();
					String accountName = eaList.get(i).getAccountName();
					int regHolidayFlg = eaList.get(i).getRegHolidayFlg();
					int invalidFlg = eaList.get(i).getInvalidFlg();

					String strRegHolidayFlg = null;
					String strInvalidFlg = null;

					if (regHolidayFlg == 1) {
						strRegHolidayFlg = "土曜・日曜は引き落としを行わない";
					} else {
						strRegHolidayFlg = "土曜・日曜も引き落としを行う";
					}

					if (invalidFlg == 1) {
						strInvalidFlg = "解約済";
					} else {
						strInvalidFlg = "利用中";
					}
			%>

			<tr>
				<td><%=accountName%></td>
				<td><%=strRegHolidayFlg%></td>
				<td><%=strInvalidFlg%></td>
				<td>
					<form action="./ACT020.jsp" method="post" style="display: inline">
						<input type="hidden" value="<%=accountId%>" name="account_id">
						<input type="submit" value="修正" class="upd_button">
					</form>
					<form action="./SvACT030" method="post" style="display: inline">
						<input type="hidden" value="<%=accountId%>" name="account_id">
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