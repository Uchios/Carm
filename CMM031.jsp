<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>クレジットカード管理</title>
<link href="./Main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<br>
	<br>
	<br>
	<form action="./SvRFR010" method="post">
		<input type="submit" value="利用明細" class="menu_button">
	</form>
	<br>
	<form action="./SvCAL010" method="post">
		<input type="submit" value="支払い予定日" class="menu_button">
	</form>
	<br>
	<form action="./SvRFR010" method="post">
		<input type="submit" value="利用可能額" class="menu_button">
	</form>
	
</body>
</html>