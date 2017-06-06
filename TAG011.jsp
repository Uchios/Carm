<%@page import="Carm.EntityCard"%>
<%@page import="Carm.EntityAnalyticalData"%>
<%@page import="Carm.DaoCarmPersonal"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="Carm.GpDateConversion"%>
<%@ page import="java.sql.Date"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset="UTF-8">
<title>クレジットカード管理システム</title>
<link href="./Main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
</head>
<body>
	<%
		request.setAttribute("page_name", "▶︎ タグを金額で比較");
		Integer userId = (Integer) session.getAttribute("user_id");
		String alert = (String) request.getAttribute("alert");
		List<EntityAnalyticalData>eadList = (ArrayList<EntityAnalyticalData>)request.getAttribute("ead_list");
		List<EntityAnalyticalData>eadList2 = (ArrayList<EntityAnalyticalData>)request.getAttribute("ead_list2");
		List<EntityCard>ecList = (ArrayList<EntityCard>)request.getAttribute("ec_list");
		String cardName = (String)request.getAttribute("card_name");
		Date endDate = (Date) request.getAttribute("end_date");
		Date startDate = (Date) request.getAttribute("start_date");

		GpDateConversion gdc1 = new GpDateConversion(startDate);
		GpDateConversion gdc2 = new GpDateConversion(endDate);
		
		if (userId == null) {
			request.setAttribute("message", "timeout");
			RequestDispatcher rd = request
			.getRequestDispatcher("/CMM010.jsp");
			rd.forward(request, response);
		}
	%>
	<jsp:include page="Header.jsp"></jsp:include>
	<div id="sub_page">
		<%
			if (alert != null) {
		%>
		<div class="alert"><%=alert%></div>
		<%
			}
		%>
		<br>集計範囲↓
		<form action="./SvTAG010" method="post">
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
			日 <br> <br> カード別に集計する <SELECT name="card_id"
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
		</div>
	<!--AJAX APIをロードする-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
   
      // Visualization API と　円グラフパッケージをロード
      google.load('visualization', '1.0', {'packages':['corechart']});
     
      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);
     
      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

      // 利用金額データテーブルを作成
      var data = new google.visualization.DataTable();
      data.addColumn('string', 'Topping');
      data.addColumn('number', 'Slices');
      data.addRows([
        <%for(int i = 0 ; i< eadList.size() ; i++){%>
        ['<%=eadList.get(i).getTagName()%>', <%=eadList.get(i).getPayment()%>],
        <%}%>
      ]);

      // 利用回数データテーブルを作成
      var data2 = new google.visualization.DataTable();
      data2.addColumn('string', 'Topping');
      data2.addColumn('number', 'Slices');
      data2.addRows([
        <%for(int i = 0 ; i< eadList2.size() ; i++){%>
        ['<%=eadList2.get(i).getTagName()%>', <%=eadList2.get(i).getCount()%>],
        <%}%>
      ]);
      
      // 図のオプションを設定
      var options = {'title':'利用金額で比較 （<%=cardName%>）',
                     'width':600,
                     'height':400};
      
      var options2 = {'title':'利用回数で比較 （<%=cardName%>）',
    		          'width':600,
                      'height':400};

      // 図をインスタンス化
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      chart.draw(data, options);
      var chart2 = new google.visualization.PieChart(document.getElementById('chart_div2'));
      chart2.draw(data2, options2);
    }
    </script>
	<div id="chart_div"></div>
	<div id="chart_div2"></div>

</body>
</html>