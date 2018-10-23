<!DOCTYPE HTML>
<html>
	<head>
		<title>用户登录</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width" />
		<meta name="keywords" content="用户登录" />
		<meta name="description" content="用户登录" />
		
		<link rel="shortcut icon" href="/favicon.ico">
		<#include "/include/resources.ftl">
	</head>

	<body>
		<div class="order">
			<form method="post" action="/order">
				<p>订单号：<input name="orderId" placeholder="订单号" type="text" /></p>
				<p><input type="submit" value="提交" /></p>
			</form>
			<p>交易结果：${dto.code}</p>
			<p>交易信息：${dto.message}</p>
			<p>交易订单ID：${dto.entity.id}</p>
			<p>交易订单号：${dto.entity.orderId}</p>
		</div>
	</body>
</html>