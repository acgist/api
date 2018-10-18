<!DOCTYPE HTML>
<html>
	<head>
		<title>交易页面</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width" />
		<meta name="keywords" content="交易页面" />
		<meta name="description" content="交易页面" />
		
		<link rel="shortcut icon" href="/favicon.ico">
		<link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="/resources/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/resources/js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="order">
			<form method="post" action="/order">
				<p>订单号：<input name="orderId" placeholder="订单号" type="text" /></p>
				<p><input type="submit" value="提交" /></p>
			</form>
			<p>交易结果：${result.code}</p>
			<p>交易订单号：${result.orderId}</p>
		</div>
	</body>
</html>